package com.awake.util.time;

/**
 * @version : 1.0
 * @ClassName: StopWatch
 * @Description: TODO
 * @Auther: awake
 * @Date: 2023/10/9 16:58
 **/

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class Stopwatch {

    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private boolean nano;

    private long from;

    private long to;

    private List<StopTag> tags;

    private StopTag lastTag;

    /**
     * 秒表开始计时，计时时间的最小单位是毫秒
     *
     * @return 开始计时的秒表对象
     */
    public static Stopwatch begin() {
        Stopwatch sw = new Stopwatch();
        sw.start();
        return sw;
    }

    /**
     * 秒表开始计时，计时时间的最小单位是毫微秒
     *
     * @return 开始计时的秒表对象
     */
    public static Stopwatch beginNano() {
        Stopwatch sw = new Stopwatch();
        sw.nano = true;
        sw.start();
        return sw;
    }

    /**
     * 创建一个秒表对象，该对象的计时时间的最小单位是毫秒
     *
     * @return 秒表对象
     */
    public static Stopwatch create() {
        return new Stopwatch();
    }

    /**
     * 创建一个秒表对象，该对象的计时时间的最小单位是毫微秒
     *
     * @return 秒表对象
     */
    public static Stopwatch createNano() {
        Stopwatch sw = new Stopwatch();
        sw.nano = true;
        return sw;
    }

    public static Stopwatch run(Runnable atom) {
        Stopwatch sw = begin();
        atom.run();
        sw.stop();
        return sw;
    }

    public static Stopwatch runNano(Runnable atom) {
        Stopwatch sw = beginNano();
        atom.run();
        sw.stop();
        return sw;
    }

    /**
     * 开始计时，并返回开始计时的时间，该时间最小单位由创建秒表时确定
     *
     * @return 开始计时的时间
     */
    public long start() {
        from = currentTime();
        to = from;
        return from;
    }

    private long currentTime() {
        return nano ? System.nanoTime() : System.currentTimeMillis();
    }

    /**
     * 记录停止时间，该时间最小单位由创建秒表时确定
     *
     * @return 自身以便链式赋值
     */
    public long stop() {
        to = currentTime();
        return to;
    }

    /**
     * @return 计时结果(ms)
     */
    public long getDuration() {
        return to - from;
    }

    /**
     * @see #getDuration()
     */
    public long du() {
        return to - from;
    }

    /**
     * 开始计时的时间，该时间最小单位由创建秒表时确定
     *
     * @return 开始计时的时间
     */
    public long getStartTime() {
        return from;
    }

    /**
     * 停止计时的时间，该时间最小单位由创建秒表时确定
     *
     * @return 停止计时的时间
     */
    public long getEndTime() {
        return to;
    }

    /**
     * 返回格式为 <code>Total: [计时时间][计时时间单位] : [计时开始时间]=>[计时结束时间]</code> 的字符串
     *
     * @return 格式为 <code>Total: [计时时间][计时时间单位] : [计时开始时间]=>[计时结束时间]</code> 的字符串
     */
    @Override
    public String toString() {
        String prefix = String.format("Total: %d%s : [%s]=>[%s]",
                this.getDuration(),
                (nano ? "ns" : "ms"),
                simpleDateFormat.format(new Date(from)),
                simpleDateFormat.format(new Date(to)));
        if (tags == null) {
            return prefix;
        }
        StringBuilder sb = new StringBuilder(prefix).append("\r\n");
        for (int i = 0; i < tags.size(); i++) {
            StopTag tag = tags.get(i);
            sb.append(String.format("  -> %5s: %dms",
                    tag.name == null ? "TAG" + i : tag.name,
                    tag.du()));
            if (i < tags.size() - 1) {
                sb.append("\r\n");
            }
        }
        return sb.toString();
    }

    public StopTag tag(String name) {
        if (tags == null) {
            tags = new LinkedList<>();
        }
        lastTag = new StopTag(name, System.currentTimeMillis(), lastTag);
        tags.add(lastTag);
        return lastTag;
    }

    public class StopTag {
        public String name;
        public long tm;
        public StopTag pre;

        public StopTag() {}

        public StopTag(String name, long tm, StopTag pre) {
            super();
            this.name = name;
            this.tm = tm;
            this.pre = pre;
        }

        public long du() {
            if (pre == null) {
                return tm - from;
            }
            return tm - pre.tm;
        }
    }

    public static void main(String[] args) {
        Stopwatch sw = Stopwatch.create();
        sw.start();
        sw.tag("[任務模塊]");
        sw.tag("[緩存模塊]");
        sw.stop();
        System.out.println("模塊加載:" + sw);
    }
}
