/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xuexiang.xrouter.model;


import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.annotation.Router;
import com.xuexiang.xrouter.enums.RouteType;
import com.xuexiang.xrouter.enums.TypeKind;

import java.util.Map;

/**
 * 路由信息
 *
 * @author xuexiang
 * @since 2018/5/17 上午12:32
 */
public class RouteInfo<T> {
    /**
     * 路由的类型
     */
    private RouteType type;
    /**
     *
     */
    private T rawType;        // Raw type of route
    /**
     * 路由目标类
     */
    private Class<?> destination;
    /**
     * 路由路径
     */
    private String path;
    /**
     * 路由所在的组名
     */
    private String group;
    /**
     * 路由的优先级【数字越小，优先级越高】
     */
    private int priority = -1;
    /**
     * 拓展属性
     */
    private int extra;              // Extra data
    /**
     * 所有参数的类型集合【key为参数的key，value为参数的类型】
     */
    private Map<String, Integer> paramsType;  // Param type

    public RouteInfo() {
    }

    /**
     * For versions of 'compiler' less than 1.0.7, contain 1.0.7
     *
     * @param type        type
     * @param destination destination
     * @param path        path
     * @param group       group
     * @param extra       extra
     * @return this
     */
    public static RouteInfo build(RouteType type, Class<?> destination, String path, String group, int priority, int extra) {
        return new RouteInfo(type, null, destination, path, group, null, priority, extra);
    }

    /**
     * For versions of 'compiler' greater than 1.0.7
     *
     * @param type        type
     * @param destination destination
     * @param path        path
     * @param group       group
     * @param paramsType  paramsType
     * @param priority    priority
     * @param extra       extra
     * @return this
     */
    public static RouteInfo build(RouteType type, Class<?> destination, String path, String group, Map<String, Integer> paramsType, int priority, int extra) {
        return new RouteInfo(type, null, destination, path, group, paramsType, priority, extra);
    }

    /**
     * Type
     *
     * @param router      router
     * @param destination destination
     * @param type        type
     */
    public RouteInfo(Router router, Class<?> destination, RouteType type) {
        this(type, null, destination, router.path(), router.group(), null, router.priority(), router.extras());
    }

    /**
     * 构建路由信息（RouterProcessor自动构造使用）
     *
     * @param router     路由的注解
     * @param rawType    被标注路由的类
     * @param type       路由的类型
     * @param paramsType 被{@link AutoWired}标注字段的信息（key为字段名，value为字段的类型{@link TypeKind}
     */
    public RouteInfo(Router router, T rawType, RouteType type, Map<String, Integer> paramsType) {
        this(type, rawType, null, router.path(), router.group(), paramsType, router.priority(), router.extras());
    }

    /**
     * 构建路由信息
     *
     * @param type        路由的类型
     * @param rawType     被标注路由的类
     * @param destination 路由目标类
     * @param path        路由的路径
     * @param group       路由所在的组
     * @param paramsType  被{@link AutoWired}标注字段的信息（key为字段名，value为字段的类型{@link TypeKind}
     * @param priority    优先级
     * @param extra       路由的附加属性
     */
    public RouteInfo(RouteType type, T rawType, Class<?> destination, String path, String group, Map<String, Integer> paramsType, int priority, int extra) {
        this.type = type;
        this.destination = destination;
        this.rawType = rawType;
        this.path = path;
        this.group = group;
        this.paramsType = paramsType;
        this.priority = priority;
        this.extra = extra;
    }

    public Map<String, Integer> getParamsType() {
        return paramsType;
    }

    public RouteInfo setParamsType(Map<String, Integer> paramsType) {
        this.paramsType = paramsType;
        return this;
    }

    public T getRawType() {
        return rawType;
    }

    public RouteInfo setRawType(T rawType) {
        this.rawType = rawType;
        return this;
    }

    public RouteType getType() {
        return type;
    }

    public RouteInfo setType(RouteType type) {
        this.type = type;
        return this;
    }

    public Class<?> getDestination() {
        return destination;
    }

    public RouteInfo setDestination(Class<?> destination) {
        this.destination = destination;
        return this;
    }

    public String getPath() {
        return path;
    }

    public RouteInfo setPath(String path) {
        this.path = path;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public RouteInfo setGroup(String group) {
        this.group = group;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public RouteInfo setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public int getExtra() {
        return extra;
    }

    public RouteInfo setExtra(int extra) {
        this.extra = extra;
        return this;
    }

    @Override
    public String toString() {
        return "RouteInfo{" +
                "type=" + type +
                ", rawType=" + rawType +
                ", destination=" + destination +
                ", path='" + path + '\'' +
                ", group='" + group + '\'' +
                ", priority=" + priority +
                ", extra=" + extra +
                '}';
    }
}