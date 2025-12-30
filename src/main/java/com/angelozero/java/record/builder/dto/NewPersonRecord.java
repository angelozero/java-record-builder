package com.angelozero.java.record.builder.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record NewPersonRecord(Integer id, String name) implements NewPersonRecordBuilder.With { }
