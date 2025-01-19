package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.Episode;
import com.netflix.api.netflix.models.Movie;
import lombok.Data;


@Data
public class ViewingHistoryDto
{
    private int historyId;
    private Movie movie;
    private Episode episode;
}
