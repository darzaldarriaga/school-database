package com.darwinsofttech.school.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public enum Reader {
    INSTANCE;

    private BufferedReader reader;

    public BufferedReader getReader() {
        return reader = new BufferedReader(new InputStreamReader(System.in));
    }
}
