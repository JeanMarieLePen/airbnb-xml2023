// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: temp.proto

package com.xml2023.mainapp;

/**
 * Protobuf type {@code com.xml2023.mainapp.getRezervacijaByIdResponse}
 */
public  final class getRezervacijaByIdResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.xml2023.mainapp.getRezervacijaByIdResponse)
    getRezervacijaByIdResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use getRezervacijaByIdResponse.newBuilder() to construct.
  private getRezervacijaByIdResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private getRezervacijaByIdResponse() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private getRezervacijaByIdResponse(
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
            com.xml2023.mainapp.RezervacijaDTO.Builder subBuilder = null;
            if (odgovor_ != null) {
              subBuilder = odgovor_.toBuilder();
            }
            odgovor_ = input.readMessage(com.xml2023.mainapp.RezervacijaDTO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(odgovor_);
              odgovor_ = subBuilder.buildPartial();
            }

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
    return com.xml2023.mainapp.Temp.internal_static_com_xml2023_mainapp_getRezervacijaByIdResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.xml2023.mainapp.Temp.internal_static_com_xml2023_mainapp_getRezervacijaByIdResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.xml2023.mainapp.getRezervacijaByIdResponse.class, com.xml2023.mainapp.getRezervacijaByIdResponse.Builder.class);
  }

  public static final int ODGOVOR_FIELD_NUMBER = 1;
  private com.xml2023.mainapp.RezervacijaDTO odgovor_;
  /**
   * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
   */
  public boolean hasOdgovor() {
    return odgovor_ != null;
  }
  /**
   * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
   */
  public com.xml2023.mainapp.RezervacijaDTO getOdgovor() {
    return odgovor_ == null ? com.xml2023.mainapp.RezervacijaDTO.getDefaultInstance() : odgovor_;
  }
  /**
   * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
   */
  public com.xml2023.mainapp.RezervacijaDTOOrBuilder getOdgovorOrBuilder() {
    return getOdgovor();
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
    if (odgovor_ != null) {
      output.writeMessage(1, getOdgovor());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (odgovor_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getOdgovor());
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
    if (!(obj instanceof com.xml2023.mainapp.getRezervacijaByIdResponse)) {
      return super.equals(obj);
    }
    com.xml2023.mainapp.getRezervacijaByIdResponse other = (com.xml2023.mainapp.getRezervacijaByIdResponse) obj;

    boolean result = true;
    result = result && (hasOdgovor() == other.hasOdgovor());
    if (hasOdgovor()) {
      result = result && getOdgovor()
          .equals(other.getOdgovor());
    }
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
    if (hasOdgovor()) {
      hash = (37 * hash) + ODGOVOR_FIELD_NUMBER;
      hash = (53 * hash) + getOdgovor().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.xml2023.mainapp.getRezervacijaByIdResponse parseFrom(
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
  public static Builder newBuilder(com.xml2023.mainapp.getRezervacijaByIdResponse prototype) {
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
   * Protobuf type {@code com.xml2023.mainapp.getRezervacijaByIdResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.xml2023.mainapp.getRezervacijaByIdResponse)
      com.xml2023.mainapp.getRezervacijaByIdResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.xml2023.mainapp.Temp.internal_static_com_xml2023_mainapp_getRezervacijaByIdResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.xml2023.mainapp.Temp.internal_static_com_xml2023_mainapp_getRezervacijaByIdResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.xml2023.mainapp.getRezervacijaByIdResponse.class, com.xml2023.mainapp.getRezervacijaByIdResponse.Builder.class);
    }

    // Construct using com.xml2023.mainapp.getRezervacijaByIdResponse.newBuilder()
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
      if (odgovorBuilder_ == null) {
        odgovor_ = null;
      } else {
        odgovor_ = null;
        odgovorBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.xml2023.mainapp.Temp.internal_static_com_xml2023_mainapp_getRezervacijaByIdResponse_descriptor;
    }

    @java.lang.Override
    public com.xml2023.mainapp.getRezervacijaByIdResponse getDefaultInstanceForType() {
      return com.xml2023.mainapp.getRezervacijaByIdResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.xml2023.mainapp.getRezervacijaByIdResponse build() {
      com.xml2023.mainapp.getRezervacijaByIdResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.xml2023.mainapp.getRezervacijaByIdResponse buildPartial() {
      com.xml2023.mainapp.getRezervacijaByIdResponse result = new com.xml2023.mainapp.getRezervacijaByIdResponse(this);
      if (odgovorBuilder_ == null) {
        result.odgovor_ = odgovor_;
      } else {
        result.odgovor_ = odgovorBuilder_.build();
      }
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
      if (other instanceof com.xml2023.mainapp.getRezervacijaByIdResponse) {
        return mergeFrom((com.xml2023.mainapp.getRezervacijaByIdResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.xml2023.mainapp.getRezervacijaByIdResponse other) {
      if (other == com.xml2023.mainapp.getRezervacijaByIdResponse.getDefaultInstance()) return this;
      if (other.hasOdgovor()) {
        mergeOdgovor(other.getOdgovor());
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
      com.xml2023.mainapp.getRezervacijaByIdResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.xml2023.mainapp.getRezervacijaByIdResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.xml2023.mainapp.RezervacijaDTO odgovor_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.xml2023.mainapp.RezervacijaDTO, com.xml2023.mainapp.RezervacijaDTO.Builder, com.xml2023.mainapp.RezervacijaDTOOrBuilder> odgovorBuilder_;
    /**
     * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
     */
    public boolean hasOdgovor() {
      return odgovorBuilder_ != null || odgovor_ != null;
    }
    /**
     * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
     */
    public com.xml2023.mainapp.RezervacijaDTO getOdgovor() {
      if (odgovorBuilder_ == null) {
        return odgovor_ == null ? com.xml2023.mainapp.RezervacijaDTO.getDefaultInstance() : odgovor_;
      } else {
        return odgovorBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
     */
    public Builder setOdgovor(com.xml2023.mainapp.RezervacijaDTO value) {
      if (odgovorBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        odgovor_ = value;
        onChanged();
      } else {
        odgovorBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
     */
    public Builder setOdgovor(
        com.xml2023.mainapp.RezervacijaDTO.Builder builderForValue) {
      if (odgovorBuilder_ == null) {
        odgovor_ = builderForValue.build();
        onChanged();
      } else {
        odgovorBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
     */
    public Builder mergeOdgovor(com.xml2023.mainapp.RezervacijaDTO value) {
      if (odgovorBuilder_ == null) {
        if (odgovor_ != null) {
          odgovor_ =
            com.xml2023.mainapp.RezervacijaDTO.newBuilder(odgovor_).mergeFrom(value).buildPartial();
        } else {
          odgovor_ = value;
        }
        onChanged();
      } else {
        odgovorBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
     */
    public Builder clearOdgovor() {
      if (odgovorBuilder_ == null) {
        odgovor_ = null;
        onChanged();
      } else {
        odgovor_ = null;
        odgovorBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
     */
    public com.xml2023.mainapp.RezervacijaDTO.Builder getOdgovorBuilder() {
      
      onChanged();
      return getOdgovorFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
     */
    public com.xml2023.mainapp.RezervacijaDTOOrBuilder getOdgovorOrBuilder() {
      if (odgovorBuilder_ != null) {
        return odgovorBuilder_.getMessageOrBuilder();
      } else {
        return odgovor_ == null ?
            com.xml2023.mainapp.RezervacijaDTO.getDefaultInstance() : odgovor_;
      }
    }
    /**
     * <code>.com.xml2023.mainapp.RezervacijaDTO odgovor = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.xml2023.mainapp.RezervacijaDTO, com.xml2023.mainapp.RezervacijaDTO.Builder, com.xml2023.mainapp.RezervacijaDTOOrBuilder> 
        getOdgovorFieldBuilder() {
      if (odgovorBuilder_ == null) {
        odgovorBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.xml2023.mainapp.RezervacijaDTO, com.xml2023.mainapp.RezervacijaDTO.Builder, com.xml2023.mainapp.RezervacijaDTOOrBuilder>(
                getOdgovor(),
                getParentForChildren(),
                isClean());
        odgovor_ = null;
      }
      return odgovorBuilder_;
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


    // @@protoc_insertion_point(builder_scope:com.xml2023.mainapp.getRezervacijaByIdResponse)
  }

  // @@protoc_insertion_point(class_scope:com.xml2023.mainapp.getRezervacijaByIdResponse)
  private static final com.xml2023.mainapp.getRezervacijaByIdResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.xml2023.mainapp.getRezervacijaByIdResponse();
  }

  public static com.xml2023.mainapp.getRezervacijaByIdResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<getRezervacijaByIdResponse>
      PARSER = new com.google.protobuf.AbstractParser<getRezervacijaByIdResponse>() {
    @java.lang.Override
    public getRezervacijaByIdResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new getRezervacijaByIdResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<getRezervacijaByIdResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<getRezervacijaByIdResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.xml2023.mainapp.getRezervacijaByIdResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
