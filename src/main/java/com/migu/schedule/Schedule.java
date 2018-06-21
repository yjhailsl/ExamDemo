package com.migu.schedule;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

/*
*类名和方法不能修改
 */
public class Schedule {

	private List<NodeInfo> nodeList;
	
	private List<Task> taskList;
	
    public int init() {
        // TODO 方法未实现 test
    	if(null != nodeList && nodeList.size() != 0)
    	{
    		nodeList.clear();
    	}
    	
    	if(null != taskList && taskList.size() != 0)
    	{
    		taskList.clear();
    	}
    	nodeList = new ArrayList<NodeInfo>();
    	taskList = new ArrayList<Task>();
        return ReturnCodeKeys.E001;
    }

	public int registerNode(int nodeId) 
	{
		if(nodeId <= 0)
		{
			return ReturnCodeKeys.E004;
		}
		
		if(null != nodeList&&nodeList.size()>0)
		{
			for(NodeInfo n : nodeList)
			{
				if(nodeId == n.getNodeId())
				{
					return ReturnCodeKeys.E005;
				}
			}
		}
		NodeInfo node = new NodeInfo();
		node.setNodeId(nodeId);
		nodeList.add(node);
        // TODO 方法未实现
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) 
    {
    	if(nodeId <= 0)
		{
			return ReturnCodeKeys.E004;
		}
    	
    	if(null != nodeList&&nodeList.size()>0)
    	{
    		for (int i = 0; i < nodeList.size(); i++) 
        	{
        		if(nodeId == nodeList.get(i).getNodeId())
        		{
        			List<Task> list = nodeList.get(i).getTaskList();
        			if(null != list && list.size() > 0)
        			{
        				for(Task t : list)
        				{
        					t.setState(-1);
        					taskList.add(t);
        				}
        			}
        			nodeList.remove(i);
    				return ReturnCodeKeys.E006;
        		}
        		else
        		{
        			return ReturnCodeKeys.E007;
        		}
        	}
    	}
    	else
    	{
    		return ReturnCodeKeys.E007;
    	}
    	return ReturnCodeKeys.E007;
    }
   


    public int addTask(int taskId, int consumption) {
        // TODO 方法未实现
    	
    	if(taskId <= 0)
    	{
    		 return ReturnCodeKeys.E009;
    	}
    	
    	//挂起中任务
    	if(null != taskList && taskList.size() > 0)
    	{
    		for(Task task : taskList)
        	{
        		if(taskId == task.getTaskId())
        		{
        			 return ReturnCodeKeys.E010;
        		}
        	}
    	}
    	//运行中任务
    	for(NodeInfo n : nodeList)
    	{
    	    if(null != n.getTaskList())
    	    {
    	        for(Task t : n.getTaskList())
                {
                    if(taskId == t.getTaskId())
                    {
                        return ReturnCodeKeys.E010;
                    }
                }
    	    }
    	}
    	Task t = new Task(taskId,-1,consumption);
    	taskList.add(t);
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) 
    {
    	if(taskId <= 0)
    	{
    		 return ReturnCodeKeys.E009;
    	}
    	//標記挂起任务中是否存在此任务
    	boolean taskFlag = false;
    	
    	//运行中任务是否存在此任务
    	boolean nodeTaskFlag = false;
    	if(null != taskList && taskList.size() > 0)
    	{
    		for(int i = 0; i<taskList.size(); i++)
        	{
        		if(taskId == taskList.get(i).getTaskId())
        		{
        			taskList.remove(i);
        			taskFlag = true;
        		}
        	}
    	}
    	
    	if(null != nodeList && nodeList.size() > 0)
    	{
    		for(NodeInfo n : nodeList)
        	{
    			if(null != n.getTaskList() && n.getTaskList().size() > 0)
    			{
    				for(int i = 0; i< n.getTaskList().size(); i++)
        			{
    					if(taskId == taskList.get(i).getTaskId())
    	        		{
    	        			taskList.remove(i);
    	        			nodeTaskFlag = true;
    	        		}
        			}
    			}
        	}
    	}
    	
    	//运行中或者挂起任务删除成功任务任务删除成功
    	if(nodeTaskFlag || taskFlag)
    	{
    		return ReturnCodeKeys.E011;
    	}
    	else
    	{
    		return ReturnCodeKeys.E012;
    	}
    }


    public int scheduleTask(int threshold) 
    {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) 
    {
    	TaskInfo tk = null;
    	if(null != tasks && tasks.size()> 0)
    	{
    		tasks.clear();
    	}
    	if(null != nodeList && nodeList.size() > 0)
    	{
    		for(NodeInfo n : nodeList)
        	{
    			if(null != n.getTaskList() && n.getTaskList().size() > 0)
    			{
    				for(Task t : n.getTaskList())
        			{
    					tk = new TaskInfo();
    	    			tk.setNodeId(n.getNodeId());
    	    			tk.setTaskId(t.getTaskId());
    	    			tasks.add(tk);
        			}
    			}
        	}
    	}
    	
    	if(null != taskList && taskList.size()> 0)
    	{
    		for(Task t : taskList)
    		{
    			tk = new TaskInfo();
    			tk.setNodeId(-1);
    			tk.setTaskId(t.getTaskId());
    			tasks.add(tk);
    		}
    	}
    	if(null == tasks || tasks.size() == 0)
    	{
    		return ReturnCodeKeys.E016;
    	}
    	Collections.sort(tasks,new Comparator<TaskInfo>() 
    	{
			public int compare(TaskInfo arg0, TaskInfo arg1) 
			{
				   int o1 = arg0.getNodeId();
				   int o2 = arg1.getNodeId();
				   return o1 - o2;
			}
        });
    	
        return ReturnCodeKeys.E015;
    }

    public List<NodeInfo> getNodeList() {
		return nodeList;
	}


	public void setNodeList(List<NodeInfo> nodeList) {
		this.nodeList = nodeList;
	}


	public List<Task> getTaskList() {
		return taskList;
	}


	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

}
