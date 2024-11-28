package com.example.service.stats;

import com.example.dto.GraphDTO;
import com.example.dto.StatsDTO;


public interface StatsService {
   public GraphDTO getChartData();
   public StatsDTO getStats();

}
