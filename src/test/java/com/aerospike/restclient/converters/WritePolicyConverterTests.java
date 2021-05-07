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
package com.aerospike.restclient.converters;

import com.aerospike.client.policy.CommitLevel;
import com.aerospike.client.policy.GenerationPolicy;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.Replica;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.restclient.util.AerospikeAPIConstants;
import com.aerospike.restclient.util.converters.policyconverters.WritePolicyConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class WritePolicyConverterTests {

	Map<String, String> policyMap;

	@Before
	public void setup() {
		policyMap = new HashMap<>();
	}

	@Test
	public void testReplica() {
		policyMap.put(AerospikeAPIConstants.REPLICA, Replica.MASTER.toString());
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertEquals(policy.replica, Replica.MASTER);
	}

	@Test
	public void testTotalTimeout() {
		policyMap.put(AerospikeAPIConstants.TOTAL_TIMEOUT, "333");
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertEquals(policy.totalTimeout, 333);
	}

	@Test
	public void testSocketTimeout() {
		policyMap.put(AerospikeAPIConstants.SOCKET_TIMEOUT, "332");
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertEquals(policy.socketTimeout, 332);
	}

	@Test
	public void testSleepBetweenRetries() {
		policyMap.put(AerospikeAPIConstants.SLEEP_BETWEEN_RETRIES, "111");
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertEquals(policy.sleepBetweenRetries, 111);
	}

	@Test
	public void testMaxRetries() {
		policyMap.put(AerospikeAPIConstants.MAX_RETRIES, "5");
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertEquals(policy.maxRetries, 5);
	}

	@Test
	public void testSendKey() {
		policyMap.put(AerospikeAPIConstants.SEND_KEY, "true");
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertTrue(policy.sendKey);
	}

	@Test
	public void testExpiration() {
		policyMap.put(AerospikeAPIConstants.EXPIRATION, "5");
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertEquals(policy.expiration, 5);
	}

	@Test
	public void testGeneration() {
		policyMap.put(AerospikeAPIConstants.GENERATION, "5");
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertEquals(policy.generation, 5);
	}

	@Test
	public void testDurableDelete() {
		policyMap.put(AerospikeAPIConstants.DURABLE_DELETE, "true");
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertTrue(policy.durableDelete);
	}

	@Test
	public void testrespondAllOps() {
		policyMap.put(AerospikeAPIConstants.RESPOND_ALL_OPS, "true");
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertTrue(policy.respondAllOps);
	}

	@Test
	public void testCommitLevel() {
		policyMap.put(AerospikeAPIConstants.COMMIT_LEVEL, CommitLevel.COMMIT_MASTER.toString());
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertEquals(policy.commitLevel, CommitLevel.COMMIT_MASTER);
	}

	@Test
	public void testRecordExistsAction() {
		policyMap.put(AerospikeAPIConstants.RECORD_EXISTS_ACTION, RecordExistsAction.UPDATE_ONLY.toString());
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertEquals(policy.recordExistsAction, RecordExistsAction.UPDATE_ONLY);
	}

	@Test
	public void testGenerationPolicy() {
		policyMap.put(AerospikeAPIConstants.GENERATION_POLICY, GenerationPolicy.EXPECT_GEN_EQUAL.toString());
		WritePolicy policy = WritePolicyConverter.writePolicyFromMap(policyMap);
		Assert.assertEquals(policy.generationPolicy, GenerationPolicy.EXPECT_GEN_EQUAL);
	}
}
