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

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.DeleteSmestajsForHostRequest,
      com.xml2023.mainapp.DeleteSmestajsForHostResponse> getDeketeSnestajsForHostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deketeSnestajsForHost",
      requestType = com.xml2023.mainapp.DeleteSmestajsForHostRequest.class,
      responseType = com.xml2023.mainapp.DeleteSmestajsForHostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.DeleteSmestajsForHostRequest,
      com.xml2023.mainapp.DeleteSmestajsForHostResponse> getDeketeSnestajsForHostMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.DeleteSmestajsForHostRequest, com.xml2023.mainapp.DeleteSmestajsForHostResponse> getDeketeSnestajsForHostMethod;
    if ((getDeketeSnestajsForHostMethod = SmestajGrpcGrpc.getDeketeSnestajsForHostMethod) == null) {
      synchronized (SmestajGrpcGrpc.class) {
        if ((getDeketeSnestajsForHostMethod = SmestajGrpcGrpc.getDeketeSnestajsForHostMethod) == null) {
          SmestajGrpcGrpc.getDeketeSnestajsForHostMethod = getDeketeSnestajsForHostMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.DeleteSmestajsForHostRequest, com.xml2023.mainapp.DeleteSmestajsForHostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.SmestajGrpc", "deketeSnestajsForHost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.DeleteSmestajsForHostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.DeleteSmestajsForHostResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SmestajGrpcMethodDescriptorSupplier("deketeSnestajsForHost"))
                  .build();
          }
        }
     }
     return getDeketeSnestajsForHostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.getSmestajByIdRequest,
      com.xml2023.mainapp.getSmestajByIdResponse> getGetSmestajByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSmestajById",
      requestType = com.xml2023.mainapp.getSmestajByIdRequest.class,
      responseType = com.xml2023.mainapp.getSmestajByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.getSmestajByIdRequest,
      com.xml2023.mainapp.getSmestajByIdResponse> getGetSmestajByIdMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.getSmestajByIdRequest, com.xml2023.mainapp.getSmestajByIdResponse> getGetSmestajByIdMethod;
    if ((getGetSmestajByIdMethod = SmestajGrpcGrpc.getGetSmestajByIdMethod) == null) {
      synchronized (SmestajGrpcGrpc.class) {
        if ((getGetSmestajByIdMethod = SmestajGrpcGrpc.getGetSmestajByIdMethod) == null) {
          SmestajGrpcGrpc.getGetSmestajByIdMethod = getGetSmestajByIdMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.getSmestajByIdRequest, com.xml2023.mainapp.getSmestajByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.SmestajGrpc", "getSmestajById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getSmestajByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getSmestajByIdResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SmestajGrpcMethodDescriptorSupplier("getSmestajById"))
                  .build();
          }
        }
     }
     return getGetSmestajByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.getListaSmestajaByUserIdRequest,
      com.xml2023.mainapp.getListaSmestajaByUserIdResponse> getGetListaSmestajaByUserIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getListaSmestajaByUserId",
      requestType = com.xml2023.mainapp.getListaSmestajaByUserIdRequest.class,
      responseType = com.xml2023.mainapp.getListaSmestajaByUserIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.getListaSmestajaByUserIdRequest,
      com.xml2023.mainapp.getListaSmestajaByUserIdResponse> getGetListaSmestajaByUserIdMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.getListaSmestajaByUserIdRequest, com.xml2023.mainapp.getListaSmestajaByUserIdResponse> getGetListaSmestajaByUserIdMethod;
    if ((getGetListaSmestajaByUserIdMethod = SmestajGrpcGrpc.getGetListaSmestajaByUserIdMethod) == null) {
      synchronized (SmestajGrpcGrpc.class) {
        if ((getGetListaSmestajaByUserIdMethod = SmestajGrpcGrpc.getGetListaSmestajaByUserIdMethod) == null) {
          SmestajGrpcGrpc.getGetListaSmestajaByUserIdMethod = getGetListaSmestajaByUserIdMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.getListaSmestajaByUserIdRequest, com.xml2023.mainapp.getListaSmestajaByUserIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.SmestajGrpc", "getListaSmestajaByUserId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getListaSmestajaByUserIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getListaSmestajaByUserIdResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SmestajGrpcMethodDescriptorSupplier("getListaSmestajaByUserId"))
                  .build();
          }
        }
     }
     return getGetListaSmestajaByUserIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.TerminZauzmiRequest,
      com.xml2023.mainapp.TerminZauzmiResponse> getZauzmiTerminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "zauzmiTermin",
      requestType = com.xml2023.mainapp.TerminZauzmiRequest.class,
      responseType = com.xml2023.mainapp.TerminZauzmiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.TerminZauzmiRequest,
      com.xml2023.mainapp.TerminZauzmiResponse> getZauzmiTerminMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.TerminZauzmiRequest, com.xml2023.mainapp.TerminZauzmiResponse> getZauzmiTerminMethod;
    if ((getZauzmiTerminMethod = SmestajGrpcGrpc.getZauzmiTerminMethod) == null) {
      synchronized (SmestajGrpcGrpc.class) {
        if ((getZauzmiTerminMethod = SmestajGrpcGrpc.getZauzmiTerminMethod) == null) {
          SmestajGrpcGrpc.getZauzmiTerminMethod = getZauzmiTerminMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.TerminZauzmiRequest, com.xml2023.mainapp.TerminZauzmiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.SmestajGrpc", "zauzmiTermin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.TerminZauzmiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.TerminZauzmiResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SmestajGrpcMethodDescriptorSupplier("zauzmiTermin"))
                  .build();
          }
        }
     }
     return getZauzmiTerminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.TerminOslobodiRequest,
      com.xml2023.mainapp.TerminOslobodiResponse> getOslobodiTerminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "oslobodiTermin",
      requestType = com.xml2023.mainapp.TerminOslobodiRequest.class,
      responseType = com.xml2023.mainapp.TerminOslobodiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.TerminOslobodiRequest,
      com.xml2023.mainapp.TerminOslobodiResponse> getOslobodiTerminMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.TerminOslobodiRequest, com.xml2023.mainapp.TerminOslobodiResponse> getOslobodiTerminMethod;
    if ((getOslobodiTerminMethod = SmestajGrpcGrpc.getOslobodiTerminMethod) == null) {
      synchronized (SmestajGrpcGrpc.class) {
        if ((getOslobodiTerminMethod = SmestajGrpcGrpc.getOslobodiTerminMethod) == null) {
          SmestajGrpcGrpc.getOslobodiTerminMethod = getOslobodiTerminMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.TerminOslobodiRequest, com.xml2023.mainapp.TerminOslobodiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.SmestajGrpc", "oslobodiTermin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.TerminOslobodiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.TerminOslobodiResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SmestajGrpcMethodDescriptorSupplier("oslobodiTermin"))
                  .build();
          }
        }
     }
     return getOslobodiTerminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.AnySmestajBelongToHostRequest,
      com.xml2023.mainapp.AnySmestajBelongToHostResponse> getBelongsToHostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "belongsToHost",
      requestType = com.xml2023.mainapp.AnySmestajBelongToHostRequest.class,
      responseType = com.xml2023.mainapp.AnySmestajBelongToHostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.AnySmestajBelongToHostRequest,
      com.xml2023.mainapp.AnySmestajBelongToHostResponse> getBelongsToHostMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.AnySmestajBelongToHostRequest, com.xml2023.mainapp.AnySmestajBelongToHostResponse> getBelongsToHostMethod;
    if ((getBelongsToHostMethod = SmestajGrpcGrpc.getBelongsToHostMethod) == null) {
      synchronized (SmestajGrpcGrpc.class) {
        if ((getBelongsToHostMethod = SmestajGrpcGrpc.getBelongsToHostMethod) == null) {
          SmestajGrpcGrpc.getBelongsToHostMethod = getBelongsToHostMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.AnySmestajBelongToHostRequest, com.xml2023.mainapp.AnySmestajBelongToHostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.SmestajGrpc", "belongsToHost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.AnySmestajBelongToHostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.AnySmestajBelongToHostResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SmestajGrpcMethodDescriptorSupplier("belongsToHost"))
                  .build();
          }
        }
     }
     return getBelongsToHostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.getOceneBySmestajIdRequest,
      com.xml2023.mainapp.getOceneBySmestajIdResponse> getGetOceneSmestajaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOceneSmestaja",
      requestType = com.xml2023.mainapp.getOceneBySmestajIdRequest.class,
      responseType = com.xml2023.mainapp.getOceneBySmestajIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.getOceneBySmestajIdRequest,
      com.xml2023.mainapp.getOceneBySmestajIdResponse> getGetOceneSmestajaMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.getOceneBySmestajIdRequest, com.xml2023.mainapp.getOceneBySmestajIdResponse> getGetOceneSmestajaMethod;
    if ((getGetOceneSmestajaMethod = SmestajGrpcGrpc.getGetOceneSmestajaMethod) == null) {
      synchronized (SmestajGrpcGrpc.class) {
        if ((getGetOceneSmestajaMethod = SmestajGrpcGrpc.getGetOceneSmestajaMethod) == null) {
          SmestajGrpcGrpc.getGetOceneSmestajaMethod = getGetOceneSmestajaMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.getOceneBySmestajIdRequest, com.xml2023.mainapp.getOceneBySmestajIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.SmestajGrpc", "getOceneSmestaja"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getOceneBySmestajIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getOceneBySmestajIdResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SmestajGrpcMethodDescriptorSupplier("getOceneSmestaja"))
                  .build();
          }
        }
     }
     return getGetOceneSmestajaMethod;
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

    /**
     */
    public void deketeSnestajsForHost(com.xml2023.mainapp.DeleteSmestajsForHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.DeleteSmestajsForHostResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeketeSnestajsForHostMethod(), responseObserver);
    }

    /**
     */
    public void getSmestajById(com.xml2023.mainapp.getSmestajByIdRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getSmestajByIdResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSmestajByIdMethod(), responseObserver);
    }

    /**
     */
    public void getListaSmestajaByUserId(com.xml2023.mainapp.getListaSmestajaByUserIdRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getListaSmestajaByUserIdResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetListaSmestajaByUserIdMethod(), responseObserver);
    }

    /**
     */
    public void zauzmiTermin(com.xml2023.mainapp.TerminZauzmiRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.TerminZauzmiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getZauzmiTerminMethod(), responseObserver);
    }

    /**
     */
    public void oslobodiTermin(com.xml2023.mainapp.TerminOslobodiRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.TerminOslobodiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOslobodiTerminMethod(), responseObserver);
    }

    /**
     */
    public void belongsToHost(com.xml2023.mainapp.AnySmestajBelongToHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.AnySmestajBelongToHostResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBelongsToHostMethod(), responseObserver);
    }

    /**
     */
    public void getOceneSmestaja(com.xml2023.mainapp.getOceneBySmestajIdRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getOceneBySmestajIdResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetOceneSmestajaMethod(), responseObserver);
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
          .addMethod(
            getDeketeSnestajsForHostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.DeleteSmestajsForHostRequest,
                com.xml2023.mainapp.DeleteSmestajsForHostResponse>(
                  this, METHODID_DEKETE_SNESTAJS_FOR_HOST)))
          .addMethod(
            getGetSmestajByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.getSmestajByIdRequest,
                com.xml2023.mainapp.getSmestajByIdResponse>(
                  this, METHODID_GET_SMESTAJ_BY_ID)))
          .addMethod(
            getGetListaSmestajaByUserIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.getListaSmestajaByUserIdRequest,
                com.xml2023.mainapp.getListaSmestajaByUserIdResponse>(
                  this, METHODID_GET_LISTA_SMESTAJA_BY_USER_ID)))
          .addMethod(
            getZauzmiTerminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.TerminZauzmiRequest,
                com.xml2023.mainapp.TerminZauzmiResponse>(
                  this, METHODID_ZAUZMI_TERMIN)))
          .addMethod(
            getOslobodiTerminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.TerminOslobodiRequest,
                com.xml2023.mainapp.TerminOslobodiResponse>(
                  this, METHODID_OSLOBODI_TERMIN)))
          .addMethod(
            getBelongsToHostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.AnySmestajBelongToHostRequest,
                com.xml2023.mainapp.AnySmestajBelongToHostResponse>(
                  this, METHODID_BELONGS_TO_HOST)))
          .addMethod(
            getGetOceneSmestajaMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.getOceneBySmestajIdRequest,
                com.xml2023.mainapp.getOceneBySmestajIdResponse>(
                  this, METHODID_GET_OCENE_SMESTAJA)))
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

    /**
     */
    public void deketeSnestajsForHost(com.xml2023.mainapp.DeleteSmestajsForHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.DeleteSmestajsForHostResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeketeSnestajsForHostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSmestajById(com.xml2023.mainapp.getSmestajByIdRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getSmestajByIdResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSmestajByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getListaSmestajaByUserId(com.xml2023.mainapp.getListaSmestajaByUserIdRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getListaSmestajaByUserIdResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetListaSmestajaByUserIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void zauzmiTermin(com.xml2023.mainapp.TerminZauzmiRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.TerminZauzmiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getZauzmiTerminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void oslobodiTermin(com.xml2023.mainapp.TerminOslobodiRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.TerminOslobodiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOslobodiTerminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void belongsToHost(com.xml2023.mainapp.AnySmestajBelongToHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.AnySmestajBelongToHostResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBelongsToHostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOceneSmestaja(com.xml2023.mainapp.getOceneBySmestajIdRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getOceneBySmestajIdResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetOceneSmestajaMethod(), getCallOptions()), request, responseObserver);
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

    /**
     */
    public com.xml2023.mainapp.DeleteSmestajsForHostResponse deketeSnestajsForHost(com.xml2023.mainapp.DeleteSmestajsForHostRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeketeSnestajsForHostMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.getSmestajByIdResponse getSmestajById(com.xml2023.mainapp.getSmestajByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetSmestajByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.getListaSmestajaByUserIdResponse getListaSmestajaByUserId(com.xml2023.mainapp.getListaSmestajaByUserIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetListaSmestajaByUserIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.TerminZauzmiResponse zauzmiTermin(com.xml2023.mainapp.TerminZauzmiRequest request) {
      return blockingUnaryCall(
          getChannel(), getZauzmiTerminMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.TerminOslobodiResponse oslobodiTermin(com.xml2023.mainapp.TerminOslobodiRequest request) {
      return blockingUnaryCall(
          getChannel(), getOslobodiTerminMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.AnySmestajBelongToHostResponse belongsToHost(com.xml2023.mainapp.AnySmestajBelongToHostRequest request) {
      return blockingUnaryCall(
          getChannel(), getBelongsToHostMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.getOceneBySmestajIdResponse getOceneSmestaja(com.xml2023.mainapp.getOceneBySmestajIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetOceneSmestajaMethod(), getCallOptions(), request);
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

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.DeleteSmestajsForHostResponse> deketeSnestajsForHost(
        com.xml2023.mainapp.DeleteSmestajsForHostRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeketeSnestajsForHostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.getSmestajByIdResponse> getSmestajById(
        com.xml2023.mainapp.getSmestajByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSmestajByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.getListaSmestajaByUserIdResponse> getListaSmestajaByUserId(
        com.xml2023.mainapp.getListaSmestajaByUserIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetListaSmestajaByUserIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.TerminZauzmiResponse> zauzmiTermin(
        com.xml2023.mainapp.TerminZauzmiRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getZauzmiTerminMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.TerminOslobodiResponse> oslobodiTermin(
        com.xml2023.mainapp.TerminOslobodiRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOslobodiTerminMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.AnySmestajBelongToHostResponse> belongsToHost(
        com.xml2023.mainapp.AnySmestajBelongToHostRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBelongsToHostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.getOceneBySmestajIdResponse> getOceneSmestaja(
        com.xml2023.mainapp.getOceneBySmestajIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetOceneSmestajaMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EXISTS = 0;
  private static final int METHODID_GET_SMESTAJ_IDS_FOR_HOST = 1;
  private static final int METHODID_DEKETE_SNESTAJS_FOR_HOST = 2;
  private static final int METHODID_GET_SMESTAJ_BY_ID = 3;
  private static final int METHODID_GET_LISTA_SMESTAJA_BY_USER_ID = 4;
  private static final int METHODID_ZAUZMI_TERMIN = 5;
  private static final int METHODID_OSLOBODI_TERMIN = 6;
  private static final int METHODID_BELONGS_TO_HOST = 7;
  private static final int METHODID_GET_OCENE_SMESTAJA = 8;

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
        case METHODID_DEKETE_SNESTAJS_FOR_HOST:
          serviceImpl.deketeSnestajsForHost((com.xml2023.mainapp.DeleteSmestajsForHostRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.DeleteSmestajsForHostResponse>) responseObserver);
          break;
        case METHODID_GET_SMESTAJ_BY_ID:
          serviceImpl.getSmestajById((com.xml2023.mainapp.getSmestajByIdRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.getSmestajByIdResponse>) responseObserver);
          break;
        case METHODID_GET_LISTA_SMESTAJA_BY_USER_ID:
          serviceImpl.getListaSmestajaByUserId((com.xml2023.mainapp.getListaSmestajaByUserIdRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.getListaSmestajaByUserIdResponse>) responseObserver);
          break;
        case METHODID_ZAUZMI_TERMIN:
          serviceImpl.zauzmiTermin((com.xml2023.mainapp.TerminZauzmiRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.TerminZauzmiResponse>) responseObserver);
          break;
        case METHODID_OSLOBODI_TERMIN:
          serviceImpl.oslobodiTermin((com.xml2023.mainapp.TerminOslobodiRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.TerminOslobodiResponse>) responseObserver);
          break;
        case METHODID_BELONGS_TO_HOST:
          serviceImpl.belongsToHost((com.xml2023.mainapp.AnySmestajBelongToHostRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.AnySmestajBelongToHostResponse>) responseObserver);
          break;
        case METHODID_GET_OCENE_SMESTAJA:
          serviceImpl.getOceneSmestaja((com.xml2023.mainapp.getOceneBySmestajIdRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.getOceneBySmestajIdResponse>) responseObserver);
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
              .addMethod(getDeketeSnestajsForHostMethod())
              .addMethod(getGetSmestajByIdMethod())
              .addMethod(getGetListaSmestajaByUserIdMethod())
              .addMethod(getZauzmiTerminMethod())
              .addMethod(getOslobodiTerminMethod())
              .addMethod(getBelongsToHostMethod())
              .addMethod(getGetOceneSmestajaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
