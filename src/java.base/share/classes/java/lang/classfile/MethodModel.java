/*
 * Copyright (c) 2022, 2024, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package java.lang.classfile;

import java.lang.classfile.constantpool.Utf8Entry;
import java.lang.constant.MethodTypeDesc;
import java.util.Optional;

import jdk.internal.classfile.impl.BufferedMethodBuilder;
import jdk.internal.classfile.impl.MethodImpl;
import jdk.internal.classfile.impl.Util;

/**
 * Models a method.  The contents of the method can be traversed via
 * a streaming view, or via random access (e.g.,
 * {@link #flags()}), or by freely mixing the two.
 *
 * @since 24
 */
public sealed interface MethodModel
        extends CompoundElement<MethodElement>, AttributedElement, ClassElement
        permits BufferedMethodBuilder.Model, MethodImpl {

    /** {@return the access flags} */
    AccessFlags flags();

    /** {@return the class model this method is a member of, if known} */
    Optional<ClassModel> parent();

    /** {@return the name of this method} */
    Utf8Entry methodName();

    /** {@return the method descriptor of this method} */
    Utf8Entry methodType();

    /** {@return the method descriptor of this method, as a symbolic descriptor} */
    default MethodTypeDesc methodTypeSymbol() {
        return Util.methodTypeSymbol(methodType());
    }

    /** {@return the body of this method, if there is one} */
    Optional<CodeModel> code();
}
