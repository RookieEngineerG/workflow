package com.hitotek.workflow.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description
 * @date 2018/10/12
 */

@Data
public class ChartModel {
    private List<Double> y = new ArrayList<>();
    private List<String> x = new ArrayList<>();

    public void pushX(String x) {
        this.x.add(x);
    }

    public void pushY(Double y) {
        this.y.add(y);
    }
}
