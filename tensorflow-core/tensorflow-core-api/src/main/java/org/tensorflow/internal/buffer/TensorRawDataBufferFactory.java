/*
 *  Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.internal.buffer;

import org.bytedeco.javacpp.Pointer;
import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.ShortDataBuffer;
import org.tensorflow.tools.buffer.impl.raw.RawDataBufferFactory;

class TensorRawDataBufferFactory extends RawDataBufferFactory {

  static ByteDataBuffer mapTensorToBytes(Pointer tensorMemory) {
    return mapNativeBytes(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static IntDataBuffer mapTensorToInts(Pointer tensorMemory) {
    return mapNativeInts(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static LongDataBuffer mapTensorToLongs(Pointer tensorMemory) {
    return mapNativeLongs(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static FloatDataBuffer mapTensorToFloats(Pointer tensorMemory) {
    return mapNativeFloats(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static DoubleDataBuffer mapTensorToDoubles(Pointer tensorMemory) {
    return mapNativeDoubles(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static ShortDataBuffer mapTensorToShorts(Pointer tensorMemory) {
    return mapNativeShorts(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static BooleanDataBuffer mapTensorToBooleans(Pointer tensorMemory) {
    return mapNativeBooleans(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static StringTensorBuffer mapTensorToStrings(Pointer tensorMemory, long numElements) {
    long offsetByteSize = numElements * Long.BYTES;
    LongDataBuffer offsets = mapNativeLongs(tensorMemory.address(), offsetByteSize, false);
    ByteDataBuffer data = mapNativeBytes(
        tensorMemory.address() + offsetByteSize,
        tensorMemory.capacity() - offsetByteSize,
        false);
    return new StringTensorBuffer(offsets, data);
  }
}
