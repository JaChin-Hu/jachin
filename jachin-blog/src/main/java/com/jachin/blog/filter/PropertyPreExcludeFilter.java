package com.jachin.blog.filter;

import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 9:55
 */
public class PropertyPreExcludeFilter extends SimplePropertyPreFilter {
    public PropertyPreExcludeFilter addExcludes(String... filters) {
        for (String filter : filters) {
            this.getExcludes().add(filter);
        }
        return this;
    }
}
