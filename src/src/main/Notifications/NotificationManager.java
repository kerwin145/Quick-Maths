package src.main.Notifications;

import java.awt.Graphics;
import java.util.ArrayList;

import src.main.GUI;

public class NotificationManager {

	GUI gui;
	
	ArrayList<Notification> notifications = new ArrayList<Notification>();
	ArrayList<Notification> removeList = new ArrayList<Notification>();
	int topMarginY = 60, spacing = 5;
	int top = topMarginY;
	
	int targetX, width;
	
	public NotificationManager(GUI gui) {
		this.gui = gui;
		width = (int) (gui.getLevSelect().Home.width * 1.8);
		targetX = gui.getLevSelect().Home.x - (width - gui.getLevSelect().Home.width);
	}
	
	public void render(Graphics g) {
		for(Notification n: notifications) {
			n.render(g);
		}
	}
	
	public void tick() {
		for(Notification n: notifications) {
			n.tick();
			if(n.getTimeOver() == true)
				removeList.add(n);
		}	
		while(removeList.size() > 0) {
			notifications.remove(removeList.get(0));
			removeList.remove(0);
			
		}
		
		for(Notification n: notifications) {
			if(n.getBox().x > targetX)
				n.getBox().x--;
		}
		if(notifications.size() > 0) {
			if (notifications.get(0).getBox().y > topMarginY) {
				for(Notification n: notifications)
					n.getBox().y-=2;
			}
			top = notifications.get(0).getBox().y;
		}
		
		
	}
	
	public void addNotification(String t) {
		Notification n = new Notification(top + (Notification.height + spacing)*(notifications.size()), width, t);
		notifications.add(n);
	}
	
}
