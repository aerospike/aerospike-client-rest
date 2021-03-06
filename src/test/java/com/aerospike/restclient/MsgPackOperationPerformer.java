/*
 * Copyright 2019 Aerospike, Inc.
 *
 * Portions may be licensed to Aerospike, Inc. under one or more contributor
 * license agreements WHICH ARE COMPATIBLE WITH THE APACHE LICENSE, VERSION 2.0.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.aerospike.restclient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MsgPackOperationPerformer implements OperationPerformer {
	ObjectMapper mapper;
	private final TypeReference<Map<String, Object>> recordType = new TypeReference<Map<String, Object>>() {};

	public MsgPackOperationPerformer() {
		this.mapper = new ObjectMapper(new MessagePackFactory());
	}

	@SuppressWarnings("unchecked")
	@Override
	public
	Map<String, Object>performOperationsAndReturn(MockMvc mockMVC, String testEndpoint, List<Map<String, Object>>ops) {
		byte[] payload = null;
		try {
			payload = mapper.writeValueAsBytes(ops);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] response = null;
		try {
			response = ASTestUtils.performOperationAndReturn(mockMVC, testEndpoint, payload);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return (Map<String, Object>)mapper.readValue(response, recordType);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void
	performOperationsAndExpect(MockMvc mockMVC, String testEndpoint, List<Map<String, Object>>ops, ResultMatcher matcher) throws Exception {
		byte[] payload = null;
		try {
			payload = mapper.writeValueAsBytes(ops);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ASTestUtils.performOperationAndExpect(mockMVC, testEndpoint, payload, matcher);

	}

	public
	byte[] performOperationsAndReturnRaw(MockMvc mockMVC, String testEndpoint, List<Map<String, Object>>ops) {
		byte[] payload = null;
		try {
			payload = mapper.writeValueAsBytes(ops);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return ASTestUtils.performOperationAndReturn(mockMVC, testEndpoint, payload);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public
	byte[] performOperationsAndReturnRaw(MockMvc mockMVC, String testEndpoint, byte[] opsByte) {
		try {
			return ASTestUtils.performOperationAndReturn(mockMVC, testEndpoint, opsByte);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}