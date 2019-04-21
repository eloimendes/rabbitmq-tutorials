/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.amqp.tutorials.tut7;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author Eloi Mendes
 */
public class Tut7Server {

	@RabbitListener(queues = "tut.rep.requests", concurrency = "100")
	public FibResponse fibonacci(int n) {
		System.out.println(" [x] Received request for " + n);
		FibResponse result = new FibResponse(n, fib(n));
		System.out.println(" [.] Returned " + result);
		return result;
	}

	public int fib(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return n;
	}

}
