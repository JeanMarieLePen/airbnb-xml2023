package com.xml2023.mainapp;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: temp.proto")
public final class SmestajGrpcGrpc {

  private SmestajGrpcGrpc() {}

  public static final String SERVICE_NAME = "com.xml2023.mainapp.SmestajGrpc";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.SmestajExistsRequest,
      com.xml2023.mainapp.SmestajExistsResponse> getExistsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "exists",
      requestType = com.xml2023.mainapp.SmestajExistsRequest.class,
      responseType = com.xml2023.mainapp.SmestajExistsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.SmestajExistsRequest,
      com.xml2023.mainapp.SmestajExistsResponse> getExistsMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.SmestajExistsRequest, com.xml2023.mainapp.SmestajExistsResponse> getExistsMethod;
    if ((getExistsMethod = SmestajGrpcGrpc.getExistsMethod) == null) {
      synchronized (SmestajGrpcGrpc.class) {
        if ((getExistsMethod = SmestajGrpcGrpc.getExistsMethod) == null) {
          SmestajGrpcGrpc.getExistsMethod = getExistsMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.SmestajExistsRequest, com.xml2023.mainapp.SmestajExistsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.SmestajGrpc", "exists"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.SmestajExistsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.SmestajExistsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SmestajGrpcMethodDescriptorSupplier("exists"))
                  .build();
          }
        }
     }
     return getExistsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.SmestajIdsForHostRequest,
      com.xml2023.mainapp.SmestajIdsForHostResponse> getGetSmestajIdsForHostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSmestajIdsForHost",
      requestType = com.xml2023.mainapp.SmestajIdsForHostRequest.class,
      responseType = com.xml2023.mainapp.SmestajIdsForHostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.SmestajIdsForHostRequest,
      com.xml2023.mainapp.SmestajIdsForHostResponse> getGetSmestajIdsForHostMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.SmestajIdsForHostRequest, com.xml2023.mainapp.SmestajIdsForHostResponse> getGetSmestajIdsForHostMethod;
    if ((getGetSmestajIdsForHostMethod = SmestajGrpcGrpc.getGetSmestajIdsForHostMethod) == null) {
      synchronized (SmestajGrpcGrpc.class) {
        if ((getGetSmestajIdsForHostMethod = SmestajGrpcGrpc.getGetSmestajIdsForHostMethod) == null) {
          SmestajGrpcGrpc.getGetSmestajIdsForHostMethod = getGetSmestajIdsForHostMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.SmestajIdsForHostRequest, com.xml2023.mainapp.SmestajIdsForHostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.SmestajGrpc", "getSmestajIdsForHost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.SmestajIdsForHostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.SmestajIdsForHostResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SmestajGrpcMethodDescriptorSupplier("getSmestajIdsForHost"))
                  .build();
          }
        }
     }
     return getGetSmestajIdsForHostMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SmestajGrpcStub newStub(io.grpc.Channel channel) {
    return new SmestajGrpcStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SmestajGrpcBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SmestajGrpcBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SmestajGrpcFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SmestajGrpcFutureStub(channel);
  }

  /**
   */
  public static abstract class SmestajGrpcImplBase implements io.grpc.BindableService {

    /**
     */
    public void exists(com.xml2023.mainapp.SmestajExistsRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.SmestajExistsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExistsMethod(), responseObserver);
    }

    /**
     */
    public void getSmestajIdsForHost(com.xml2023.mainapp.SmestajIdsForHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.SmestajIdsForHostResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSmestajIdsForHostMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getExistsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.SmestajExistsRequest,
                com.xml2023.mainapp.SmestajExistsResponse>(
                  this, METHODID_EXISTS)))
          .addMethod(
            getGetSmestajIdsForHostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.SmestajIdsForHostRequest,
                com.xml2023.mainapp.SmestajIdsForHostResponse>(
                  this, METHODID_GET_SMESTAJ_IDS_FOR_HOST)))
          .build();
    }
  }

  /**
   */
  public static final class SmestajGrpcStub extends io.grpc.stub.AbstractStub<SmestajGrpcStub> {
    private SmestajGrpcStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmestajGrpcStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmestajGrpcStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmestajGrpcStub(channel, callOptions);
    }

    /**
     */
    public void exists(com.xml2023.mainapp.SmestajExistsRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.SmestajExistsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExistsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSmestajIdsForHost(com.xml2023.mainapp.SmestajIdsForHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.SmestajIdsForHostResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSmestajIdsForHostMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SmestajGrpcBlockingStub extends io.grpc.stub.AbstractStub<SmestajGrpcBlockingStub> {
    private SmestajGrpcBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmestajGrpcBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmestajGrpcBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmestajGrpcBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.xml2023.mainapp.SmestajExistsResponse exists(com.xml2023.mainapp.SmestajExistsRequest request) {
      return blockingUnaryCall(
          getChannel(), getExistsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.SmestajIdsForHostResponse getSmestajIdsForHost(com.xml2023.mainapp.SmestajIdsForHostRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetSmestajIdsForHostMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SmestajGrpcFutureStub extends io.grpc.stub.AbstractStub<SmestajGrpcFutureStub> {
    private SmestajGrpcFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmestajGrpcFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmestajGrpcFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmestajGrpcFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.SmestajExistsResponse> exists(
        com.xml2023.mainapp.SmestajExistsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getExistsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.SmestajIdsForHostResponse> getSmestajIdsForHost(
        com.xml2023.mainapp.SmestajIdsForHostRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSmestajIdsForHostMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EXISTS = 0;
  private static final int METHODID_GET_SMESTAJ_IDS_FOR_HOST = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SmestajGrpcImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SmestajGrpcImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXISTS:
          serviceImpl.exists((com.xml2023.mainapp.SmestajExistsRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.SmestajExistsResponse>) responseObserver);
          break;
        case METHODID_GET_SMESTAJ_IDS_FOR_HOST:
          serviceImpl.getSmestajIdsForHost((com.xml2023.mainapp.SmestajIdsForHostRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.SmestajIdsForHostResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SmestajGrpcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SmestajGrpcBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.xml2023.mainapp.Temp.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SmestajGrpc");
    }
  }

  private static final class SmestajGrpcFileDescriptorSupplier
      extends SmestajGrpcBaseDescriptorSupplier {
    SmestajGrpcFileDescriptorSupplier() {}
  }

  private static final class SmestajGrpcMethodDescriptorSupplier
      extends SmestajGrpcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SmestajGrpcMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SmestajGrpcGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SmestajGrpcFileDescriptorSupplier())
              .addMethod(getExistsMethod())
              .addMethod(getGetSmestajIdsForHostMethod())
              .build();
        }
      }
    }
    return result;
  }
}
