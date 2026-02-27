package com.kaizen.todo_list.model;


public enum TaskStatus {
    TODO(0),
    DOING(1),
    DONE(2),
    CANCELED(3);

    TaskStatus(int statusCode) {

    }
}
