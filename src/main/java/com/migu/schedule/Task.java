/**
 * 
 */
package com.migu.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author CHINA
 *
 */
public class Task {

	private int taskId;
	private int state;
	
	private int consumption;

    public Task(int taskId,int state, int consumption)
    {
    	this.taskId = taskId;
    	this.state = state;
    	this.consumption = consumption;
    }
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public int getConsumption() {
		return consumption;
	}
	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}
	
	
}
