package com.example.team21;

import java.net.URL;

import org.json.rpc.client.HttpJsonRpcClientTransport;
import org.json.rpc.client.JsonRpcInvoker;


public class CRPC {
	public static Rpc getRPC()  {
		Rpc rpc = null;
		try {
			String url = "http://10.0.2.2:8888/jsonrpc";

			HttpJsonRpcClientTransport transport = null;
			transport = new HttpJsonRpcClientTransport(new URL(url));
			JsonRpcInvoker invoker = new JsonRpcInvoker();

			rpc = invoker.get(transport, "rpc", Rpc.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rpc;
	}

}
