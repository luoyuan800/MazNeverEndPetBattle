package main;

import main.task.DeleteMessage;
import main.task.DeleteNetPet;
import main.task.NetPetBattle;
import main.task.PalaceSort;
import rest.RestConnection;
import util.Content;

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
    public void addTask(Runnable task, long delay, long interval, TimeUnit timeUnit){
        executor.scheduleAtFixedRate(task, 1, interval, timeUnit);
    }

    public static void main(String...args){
        Content.log("定时服务器运行开始");
        TaskRunner runner = new TaskRunner();
        RestConnection restConnection = new RestConnection();
        Content.log("添加竞技任务");
        NetPetBattle netPetBattle = new NetPetBattle(restConnection);
        runner.addTask(netPetBattle,30, TimeUnit.MINUTES);
        Content.log("添加清除消息任务");
        runner.addTask(new DeleteMessage(restConnection), 35, TimeUnit.MINUTES);
        Content.log("添加殿堂排序任务");
        runner.addTask(new PalaceSort(restConnection), 3, 24, TimeUnit.HOURS);
        Content.log("添加宠物清除任务");
        runner.addTask(new DeleteNetPet(restConnection), 7, 14, TimeUnit.DAYS);
    }
}
