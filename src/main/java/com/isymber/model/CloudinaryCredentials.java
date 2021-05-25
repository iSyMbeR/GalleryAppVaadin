package com.isymber.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class CloudinaryCredentials extends AbstractModel {
    private String cloud_name;
    private String api_key;
    private String api_secret;
}
