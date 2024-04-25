package me.parkdaiho.project.domain;

import java.util.List;

public interface Pollable {

    List<Poll> getPollList();
    void syncWithPollList(Long good, Long bad);
    Long getGood();
    Long getBad();
}
