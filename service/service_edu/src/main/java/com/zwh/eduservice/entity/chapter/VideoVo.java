package com.zwh.eduservice.entity.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoVo {

    private String id;

    private String title;

    @ApiModelProperty(value = "云端视频资源id")
    private String videoSourceId;
}
