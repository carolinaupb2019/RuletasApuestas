package com.pruebamasivian.ruletaapuesta.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

	// Threadsafe pool of network connections
	JedisPool pool;

	// Redis connection
	Jedis jedis;

	/**
	 * 
	 * Returns a direct connection to a Redis database in localhost
	 * 
	 * using the default port.
	 *
	 * 
	 * 
	 * @return a connection to Redis
	 * 
	 * @see Image
	 * 
	 */

	public Jedis getDirectConnection() {

		jedis = new Jedis("localhost");
		return jedis;
	}

	/**
	 * 
	 * Close a connection to a Redis database
	 *
	 * 
	 * 
	 */
	public void closeDirectConnection() {

		if (jedis != null) {
			jedis.close();
		}

	}

	/**
	 * 
	 * Returns a connection from a pool to a Redis database in
	 * 
	 * localhost using the default port
	 * 
	 * 
	 * 
	 * @return a connection from a pool to Redis
	 * 
	 */
	public Jedis getConnection() {

		pool = new JedisPool(new JedisPoolConfig(), "localhost");
		jedis = pool.getResource();
		return jedis;

	}

	/**
	 * 
	 * Destroys a pool of network connections and close the connection
	 *
	 * 
	 * 
	 */
	public void destroyPool() {

		// Close the connection

		if (jedis != null) {
			jedis.close();
		}

		// Destroy the pool

		if (pool != null) {
			pool.destroy();
		}

	}

}