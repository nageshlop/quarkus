package io.quarkus.grpc.server.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.examples.helloworld3.Greeter3Grpc;
import io.grpc.examples.helloworld3.HelloReply3;
import io.grpc.examples.helloworld3.HelloRequest3;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.runtime.annotations.GrpcService;
import io.smallrye.common.annotation.Blocking;

@Singleton
public class GrpcCallWithinBlockingService extends Greeter3Grpc.Greeter3ImplBase {

    @Inject
    @GrpcService("greeter")
    GreeterGrpc.GreeterBlockingStub greeter;

    @Override
    @Blocking
    public void sayHello(HelloRequest3 request, StreamObserver<HelloReply3> responseObserver) {
        HelloReply reply = greeter.sayHello(HelloRequest.newBuilder().setName(request.getName()).build());
        responseObserver.onNext(HelloReply3.newBuilder().setMessage("response:" + reply.getMessage()).build());
        responseObserver.onCompleted();
    }
}
