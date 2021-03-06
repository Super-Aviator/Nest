package com.xqk.nest.websocket.dto;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("unused")
public class ChatMessageDTO {
    @JSONField(ordinal = 1)
    private String username;

    @JSONField(ordinal = 2)
    private String avatar;

    @JSONField(ordinal = 3,serialize = false)
    private String id;//接收者id

    @JSONField(ordinal = 4)
    private String type;

    @JSONField(ordinal = 5)
    private String content;

    @JSONField(ordinal = 6)
    private boolean mine;

    @JSONField(ordinal = 7)
    private String fromid;//发送者id

    @JSONField(ordinal = 8)
    private long timestamp;

    public ChatMessageDTO() {
        timestamp=System.currentTimeMillis();
    }

    public ChatMessageDTO(String username, String avatar, String id, String type, String content, boolean mine, String fromid) {
        this.username = username;
        this.avatar = avatar;
        this.id = id;
        this.type = type;
        this.content = content;
        this.mine = mine;
        this.fromid = fromid;
        this.timestamp = System.currentTimeMillis();
    }

    public ChatMessageDTO(String username, String avatar, String content, String fromid, long timestamp) {
        this.username = username;
        this.avatar = avatar;
        this.content = content;
        this.fromid = fromid;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    @JSONField(name = "id")
    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", mine=" + mine +
                ", fromid='" + fromid + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
