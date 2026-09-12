package com.app.multiagent.service;

public interface Agent <I, O> {

    String name();

    O execute(I input);
}
