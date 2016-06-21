package main;

import rest.RestConnection;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by gluo on 6/21/2016.
 */
public class TaskRunner {
    ScheduledExecutorService executor;
    public TaskRunner(){
        executor = Executors.newSingleThreadScheduledExecutor();
    }
    public void addTaskMilliseconds(Runnable task, long delay, long interval){
        executor.scheduleAtFixedRate(task,delay, interval, TimeUnit.MILLISECONDS);
    }
    public void addTaskSeconds(Runnable task, long delay, long interval){
        executor.scheduleAtFixedRate(task,delay, interval, TimeUnit.SECONDS);
    }
    public void addTaskSeconds(Runnable task, long interval){
        addTaskSeconds(task, 10, interval);
    }
    public void addTaskMilliSeconds(Runnable task, long interval){
        addTaskMilliseconds(task, 10000, interval);
    }

    public void addTask(Runnable task, long interval, TimeUnit timeUnit){
        executor.scheduleAtFixedRate(task, 1, interval, timeUnit);
    }

    public static void main(String...args){
        System.out.println("定时服务器运行开始");
        TaskRunner runner = new TaskRunner();
        RestConnection restConnection = new RestConnection();
        System.out.println("添加竞技任务");
        NetPetBattle netPetBattle = new NetPetBattle(restConnection);
        runner.addTaskSeconds(netPetBattle,60);
    }
}
