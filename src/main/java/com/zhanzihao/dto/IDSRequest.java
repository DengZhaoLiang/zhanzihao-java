package com.zhanzihao.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zhanzihao
 * 2021-02-01
 */
@Data
public class IDSRequest {

    /**
     * 需要批量操作的Ids
     */
    private List<Long> ids;
}
