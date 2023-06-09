package com.slotegrator.tests.rest.players.data.responses;

import lombok.Data;

@Data
public class UserRp {
	private String role;
	private Object isReport;
	private String createdAt;
	private Boolean feedback;
	private String createBy;
	private String updatedAt;
	private String surname;
	private String name;
	private Object report;
	private Object comment;
	private String id;
	private String position;
	private String email;
	private String status;
}