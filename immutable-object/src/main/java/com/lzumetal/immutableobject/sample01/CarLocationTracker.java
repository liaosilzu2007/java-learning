package com.lzumetal.immutableobject.sample01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liaosi
 * @date 2021-08-21
 */
public class CarLocationTracker {

    /**
     * 车辆编码对应车辆位置信息map，key是不同车辆的唯一编码
     */
    private Map<String, Location> locationMap = new ConcurrentHashMap<>();


    /**
     * 更新车辆的位置信息
     * <p>
     * 替换整个location对像
     *
     * @param carCode     车辆编码
     * @param newLocation 位置信息
     */
    public void updateLocation(String carCode, Location newLocation) {
        Location location = locationMap.get(carCode);
        locationMap.put(carCode, newLocation);
    }


    /**
     * 获取车里位置
     *
     * @param carCode 车辆唯一编码
     * @return 车辆位置
     */
    public Location getLocation(String carCode) {
        return locationMap.get(carCode);
    }

}
