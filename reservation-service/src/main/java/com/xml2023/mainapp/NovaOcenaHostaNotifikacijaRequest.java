// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: temp.proto

package com.xml2023.mainapp;

/**
 * Protobuf type {@code com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest}
 */
public  final class NovaOcenaHostaNotifikacijaRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest)
    NovaOcenaHostaNotifikacijaRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use NovaOcenaHostaNotifikacijaRequest.newBuilder() to construct.
  private NovaOcenaHostaNotifikacijaRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NovaOcenaHostaNotifikacijaRequest() {
    idKorisnika_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private NovaOcenaHostaNotifikacijaRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            idKorisnika_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.xml2023.mainapp.Temp.internal_static_com_xml2023_mainapp_NovaOcenaHostaNotifikacijaRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.xml2023.mainapp.Temp.internal_static_com_xml2023_mainapp_NovaOcenaHostaNotifikacijaRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest.class, com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest.Builder.class);
  }

  public static final int IDKORISNIKA_FIELD_NUMBER = 1;
  private volatile java.lang.Object idKorisnika_;
  /**
   * <code>string idKorisnika = 1;</code>
   */
  public java.lang.String getIdKorisnika() {
    java.lang.Object ref = idKorisnika_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      idKorisnika_ = s;
      return s;
    }
  }
  /**
   * <code>string idKorisnika = 1;</code>
   */
  public com.google.protobuf.ByteString
      getIdKorisnikaBytes() {
    java.lang.Object ref = idKorisnika_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      idKorisnika_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getIdKorisnikaBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, idKorisnika_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getIdKorisnikaBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, idKorisnika_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest)) {
      return super.equals(obj);
    }
    com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest other = (com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest) obj;

    boolean result = true;
    result = result && getIdKorisnika()
        .equals(other.getIdKorisnika());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + IDKORISNIKA_FIELD_NUMBER;
    hash = (53 * hash) + getIdKorisnika().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest)
      com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.xml2023.mainapp.Temp.internal_static_com_xml2023_mainapp_NovaOcenaHostaNotifikacijaRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.xml2023.mainapp.Temp.internal_static_com_xml2023_mainapp_NovaOcenaHostaNotifikacijaRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest.class, com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest.Builder.class);
    }

    // Construct using com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      idKorisnika_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.xml2023.mainapp.Temp.internal_static_com_xml2023_mainapp_NovaOcenaHostaNotifikacijaRequest_descriptor;
    }

    @java.lang.Override
    public com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest getDefaultInstanceForType() {
      return com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest build() {
      com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest buildPartial() {
      com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest result = new com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest(this);
      result.idKorisnika_ = idKorisnika_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest) {
        return mergeFrom((com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest other) {
      if (other == com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest.getDefaultInstance()) return this;
      if (!other.getIdKorisnika().isEmpty()) {
        idKorisnika_ = other.idKorisnika_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object idKorisnika_ = "";
    /**
     * <code>string idKorisnika = 1;</code>
     */
    public java.lang.String getIdKorisnika() {
      java.lang.Object ref = idKorisnika_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        idKorisnika_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string idKorisnika = 1;</code>
     */
    public com.google.protobuf.ByteString
        getIdKorisnikaBytes() {
      java.lang.Object ref = idKorisnika_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        idKorisnika_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string idKorisnika = 1;</code>
     */
    public Builder setIdKorisnika(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      idKorisnika_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string idKorisnika = 1;</code>
     */
    public Builder clearIdKorisnika() {
      
      idKorisnika_ = getDefaultInstance().getIdKorisnika();
      onChanged();
      return this;
    }
    /**
     * <code>string idKorisnika = 1;</code>
     */
    public Builder setIdKorisnikaBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      idKorisnika_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest)
  }

  // @@protoc_insertion_point(class_scope:com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest)
  private static final com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest();
  }

  public static com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NovaOcenaHostaNotifikacijaRequest>
      PARSER = new com.google.protobuf.AbstractParser<NovaOcenaHostaNotifikacijaRequest>() {
    @java.lang.Override
    public NovaOcenaHostaNotifikacijaRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new NovaOcenaHostaNotifikacijaRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NovaOcenaHostaNotifikacijaRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NovaOcenaHostaNotifikacijaRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

