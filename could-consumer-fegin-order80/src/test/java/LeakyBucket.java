public class LeakyBucket {
    /*
    水流出速率
     */
    private double rate;
    /*
    桶的大小
     */
    private double burst;

    /*
    最后更新时间
     */
    private long refreshTime;
    /*
    桶里面的水量
     */
    private int water;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getBurst() {
        return burst;
    }

    public void setBurst(double burst) {
        this.burst = burst;
    }

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }
}
