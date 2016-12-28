package com.springapp.mvc;

import com.springapp.mvc.domain.Flow;
import com.springapp.mvc.service.ClassifyService;
import com.springapp.mvc.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/12/28.
 */
@Service
public class SourceLoader  implements ApplicationListener<ContextRefreshedEvent>{

    private long pointer;
    private long fileLen;
    @Autowired
    private ClassifyService service;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){
            System.out.println("加载");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                    while (true){
                        ScheduledFuture future = scheduler.schedule(new Callable() {
                            @Override
                            public List<Flow> call() {
                                List<Flow> list = new ArrayList<Flow>();
                                try {
                                    RandomAccessFile randomAccessFile = new RandomAccessFile(Constant.dir, "r");

                                    while (fileLen != randomAccessFile.length()) {
                                        randomAccessFile.seek(pointer);
                                        String line;
                                        while ((line = randomAccessFile.readLine()) != null) {
                                            list.add(changeToFlow(line));
                                        }
                                        pointer = randomAccessFile.getFilePointer();
                                        fileLen = randomAccessFile.length();
                                        System.out.println("文件指针 "+pointer);
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return list;
                            }
                        }, 3, TimeUnit.SECONDS);

                        while (!future.isDone()){}

                        try {
                            List<Flow> list = (List<Flow>) future.get();
                            System.out.println("读取到 "+list.size());
                            if(list.size()>0){
//                                for(Flow flow:list){
//                                    service.classify(flow);
//                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();
        }
    }

    private Flow changeToFlow(String line) {
        String[] sArr = line.split(",");
        double[] d = new double[19];
        for (int i = 0; i < 19; i++){
            d[i] = Double.parseDouble(sArr[i]);
        }
        Flow flow = new Flow();
        flow.setType(sArr[19]);
        flow.setD(d);
        return flow;
    }
}
