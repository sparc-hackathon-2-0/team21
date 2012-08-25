package com.example.team21;

import java.net.URL;

import org.json.rpc.client.HttpJsonRpcClientTransport;
import org.json.rpc.client.JsonRpcInvoker;


public class CRPC {
	public static Rpc getRPC(String url) throws Exception  {
		Rpc rpc = null;
		try {
			HttpJsonRpcClientTransport transport = null;
			transport = new HttpJsonRpcClientTransport(new URL(url));
			JsonRpcInvoker invoker = new JsonRpcInvoker();

			rpc = invoker.get(transport, "rpc", Rpc.class);
		} catch (Exception e) {
			throw new Exception("Error reading from server.");
			e.printStackTrace();
		}
		return rpc;
	}

}
