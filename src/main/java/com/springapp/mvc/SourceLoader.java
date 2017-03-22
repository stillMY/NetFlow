package com.springapp.mvc;

import com.springapp.mvc.domain.Flow;
import com.springapp.mvc.service.ClassifyService;
import com.springapp.mvc.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/12/28.
 */
@Service
public class SourceLoader implements ApplicationListener<ContextRefreshedEvent> {

    private long pointer;
    private long fileLen;
    @Autowired
    private ClassifyService service;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            System.out.println("加载");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//                    while (true) {
                        ScheduledFuture future = scheduler.schedule(new Callable() {
                            @Override
                            public List<Flow> call() {
                                List<Flow> list = new ArrayList<Flow>();
                                try {
                                    File dir = new File("C:\\Users\\Administrator\\Documents\\Tencent Files\\971266976\\FileRecv\\我的所有类实验数据");
                                    for (File file : dir.listFiles()) {
                                        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

                                        while (fileLen != randomAccessFile.length()) {
                                            randomAccessFile.seek(pointer);
                                            String line;
                                            int count = 0;
                                            while ((line = randomAccessFile.readLine()) != null) {
                                                count++;
                                                if (count < Constant.TEST_START) continue;
                                                list.add(changeToFlow(line,file.getName().split("_")[0]));
                                                if(count> Constant.TEST_END) break;
                                            }
                                            pointer = randomAccessFile.getFilePointer();
                                            fileLen = randomAccessFile.length();
                                            System.out.println("文件指针 " + pointer);
                                        }
                                        pointer = 0;
                                        fileLen = 0;
                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return list;
                            }
                        }, 5, TimeUnit.SECONDS);

                        while (!future.isDone()) {
                        }

                        try {
                            List<Flow> list = (List<Flow>) future.get();
                            Collections.shuffle(list);
                            System.out.println("读取到 " + list.size());
                            if (list.size() > 0) {
                                int c = 0;
//                                for(int i=0;i<=700;i+=100){
//                                    ClassifyService service = new ClassifyService();
//                                    service.init();
//                                    Constant.TRAINNUM += 100;
                                    for (Flow flow : list) {
                                        service.classify(flow);
                                        System.out.println("数据集"+Constant.TRAINNUM+" 第"+ c++ +"条");
                                    }
                                    service.saveResult();
//                                }
                            }
                            Constant.done = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
//                }
            })
//                    .start()
            ;
        }
    }

    private Flow changeToFlow(String line, String type) {
        String[] sArr = line.split(",");
        double[] d = new double[19];
        for (int i = 0; i < 19; i++) {
            d[i] = Double.parseDouble(sArr[i]);
        }
        Flow flow = new Flow();
        flow.setType(type);
        flow.setD(d);
        return flow;
    }
}
