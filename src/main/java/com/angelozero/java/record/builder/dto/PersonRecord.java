package com.angelozero.java.record.builder.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record PersonRecord(Integer id, String name) { }
