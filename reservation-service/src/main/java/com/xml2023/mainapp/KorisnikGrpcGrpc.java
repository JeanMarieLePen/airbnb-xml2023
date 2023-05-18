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
public final class KorisnikGrpcGrpc {

  private KorisnikGrpcGrpc() {}

  public static final String SERVICE_NAME = "com.xml2023.mainapp.KorisnikGrpc";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.getHostRequest,
      com.xml2023.mainapp.getHostResponse> getGetHostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getHost",
      requestType = com.xml2023.mainapp.getHostRequest.class,
      responseType = com.xml2023.mainapp.getHostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.getHostRequest,
      com.xml2023.mainapp.getHostResponse> getGetHostMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.getHostRequest, com.xml2023.mainapp.getHostResponse> getGetHostMethod;
    if ((getGetHostMethod = KorisnikGrpcGrpc.getGetHostMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getGetHostMethod = KorisnikGrpcGrpc.getGetHostMethod) == null) {
          KorisnikGrpcGrpc.getGetHostMethod = getGetHostMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.getHostRequest, com.xml2023.mainapp.getHostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "getHost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getHostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getHostResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("getHost"))
                  .build();
          }
        }
     }
     return getGetHostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.rezOtkazanaRequest,
      com.xml2023.mainapp.rezOtkazanaResponse> getRezOtkazanaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rezOtkazana",
      requestType = com.xml2023.mainapp.rezOtkazanaRequest.class,
      responseType = com.xml2023.mainapp.rezOtkazanaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.rezOtkazanaRequest,
      com.xml2023.mainapp.rezOtkazanaResponse> getRezOtkazanaMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.rezOtkazanaRequest, com.xml2023.mainapp.rezOtkazanaResponse> getRezOtkazanaMethod;
    if ((getRezOtkazanaMethod = KorisnikGrpcGrpc.getRezOtkazanaMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getRezOtkazanaMethod = KorisnikGrpcGrpc.getRezOtkazanaMethod) == null) {
          KorisnikGrpcGrpc.getRezOtkazanaMethod = getRezOtkazanaMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.rezOtkazanaRequest, com.xml2023.mainapp.rezOtkazanaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "rezOtkazana"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.rezOtkazanaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.rezOtkazanaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("rezOtkazana"))
                  .build();
          }
        }
     }
     return getRezOtkazanaMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KorisnikGrpcStub newStub(io.grpc.Channel channel) {
    return new KorisnikGrpcStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KorisnikGrpcBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new KorisnikGrpcBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KorisnikGrpcFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new KorisnikGrpcFutureStub(channel);
  }

  /**
   */
  public static abstract class KorisnikGrpcImplBase implements io.grpc.BindableService {

    /**
     */
    public void getHost(com.xml2023.mainapp.getHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getHostResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetHostMethod(), responseObserver);
    }

    /**
     */
    public void rezOtkazana(com.xml2023.mainapp.rezOtkazanaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.rezOtkazanaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRezOtkazanaMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetHostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.getHostRequest,
                com.xml2023.mainapp.getHostResponse>(
                  this, METHODID_GET_HOST)))
          .addMethod(
            getRezOtkazanaMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.rezOtkazanaRequest,
                com.xml2023.mainapp.rezOtkazanaResponse>(
                  this, METHODID_REZ_OTKAZANA)))
          .build();
    }
  }

  /**
   */
  public static final class KorisnikGrpcStub extends io.grpc.stub.AbstractStub<KorisnikGrpcStub> {
    private KorisnikGrpcStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KorisnikGrpcStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KorisnikGrpcStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KorisnikGrpcStub(channel, callOptions);
    }

    /**
     */
    public void getHost(com.xml2023.mainapp.getHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getHostResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetHostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rezOtkazana(com.xml2023.mainapp.rezOtkazanaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.rezOtkazanaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRezOtkazanaMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class KorisnikGrpcBlockingStub extends io.grpc.stub.AbstractStub<KorisnikGrpcBlockingStub> {
    private KorisnikGrpcBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KorisnikGrpcBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KorisnikGrpcBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KorisnikGrpcBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.xml2023.mainapp.getHostResponse getHost(com.xml2023.mainapp.getHostRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetHostMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.rezOtkazanaResponse rezOtkazana(com.xml2023.mainapp.rezOtkazanaRequest request) {
      return blockingUnaryCall(
          getChannel(), getRezOtkazanaMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class KorisnikGrpcFutureStub extends io.grpc.stub.AbstractStub<KorisnikGrpcFutureStub> {
    private KorisnikGrpcFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KorisnikGrpcFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KorisnikGrpcFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KorisnikGrpcFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.getHostResponse> getHost(
        com.xml2023.mainapp.getHostRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetHostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.rezOtkazanaResponse> rezOtkazana(
        com.xml2023.mainapp.rezOtkazanaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRezOtkazanaMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_HOST = 0;
  private static final int METHODID_REZ_OTKAZANA = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final KorisnikGrpcImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(KorisnikGrpcImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_HOST:
          serviceImpl.getHost((com.xml2023.mainapp.getHostRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.getHostResponse>) responseObserver);
          break;
        case METHODID_REZ_OTKAZANA:
          serviceImpl.rezOtkazana((com.xml2023.mainapp.rezOtkazanaRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.rezOtkazanaResponse>) responseObserver);
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

  private static abstract class KorisnikGrpcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KorisnikGrpcBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.xml2023.mainapp.Temp.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KorisnikGrpc");
    }
  }

  private static final class KorisnikGrpcFileDescriptorSupplier
      extends KorisnikGrpcBaseDescriptorSupplier {
    KorisnikGrpcFileDescriptorSupplier() {}
  }

  private static final class KorisnikGrpcMethodDescriptorSupplier
      extends KorisnikGrpcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    KorisnikGrpcMethodDescriptorSupplier(String methodName) {
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
      synchronized (KorisnikGrpcGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KorisnikGrpcFileDescriptorSupplier())
              .addMethod(getGetHostMethod())
              .addMethod(getRezOtkazanaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
