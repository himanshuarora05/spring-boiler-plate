package com.company.dao.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author mukulbansal
 */
@Entity
@Table(name = "cu_cp_session_info")
@Getter
@Setter
public class CuCpSessionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int    id;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "data")
    private String data;
    @Column(name = "expiry")
    private long expiry;
    @Column(name = "stale")
    private int stale;
    @Column(name = "method")
    private String method;
    @Column(name = "created_at")
    private long createdAt;
    @Column(name = "modified_at")
    private long modifiedAt;
}
