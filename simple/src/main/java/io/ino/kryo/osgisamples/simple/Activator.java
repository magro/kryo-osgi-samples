/*
 * Copyright (c) 2013 Martin Grotzke (http://ino.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.ino.kryo.osgisamples.simple;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class Activator implements BundleActivator {
    
	private Kryo kryo;

    public void start(BundleContext context) throws Exception {
		kryo = new Kryo();

		try {
			testString();
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
    }

	private void testString() {
		String obj = "foo";
		String deserialized = deserialize(serialize(obj), String.class);
		System.out.println("Serializing String: " + (deserialized.equals(obj) ? "OK" : "Failed"));
	}

    public void stop(BundleContext context) throws Exception {
    }

    private byte[] serialize(final Object o) {
        if ( o == null ) {
            throw new NullPointerException( "Can't serialize null" );
        }

        final Output output = new Output(4096);
        kryo.writeObject(output, o);
        output.flush();
        return output.getBuffer();
    }

    private <T> T deserialize(final byte[] in, final Class<T> clazz) {
        final Input input = new Input(in);
        return kryo.readObject(input, clazz);
    }

}
