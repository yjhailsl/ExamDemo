/**
 * 
 */
package com.migu.schedule;

import java.util.List;

/**
 * @author CHINA
 *
 */
public class NodeInfo 
{
	private int nodeId;

	private int consumption;

	private List<Task> taskList;

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public int getConsumption() {
		return consumption;
	}

	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}
}
