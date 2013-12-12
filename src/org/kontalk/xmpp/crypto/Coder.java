/*
 * Kontalk Android client
 * Copyright (C) 2013 Kontalk Devteam <devteam@kontalk.org>

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kontalk.xmpp.crypto;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;


/**
 * Generic coder interface.
 * @author Daniele Ricci
 */
public interface Coder {

    /*
     * Security flags for encryption features.
     * These flags marks only features found in a message.
     */

    /** Cleartext messages. Not encrypted nor signed. */
    public static final int SECURITY_CLEARTEXT = 0;
    /** Legacy (2.x) encryption method. For compatibility with old messages. */
    public static final int SECURITY_LEGACY_ENCRYPTED = 1;
    /** Basic encryption (e.g. PGP encrypted). Safe enough. */
    public static final int SECURITY_BASIC_ENCRYPTED = 1 << 1;
    /** Basic signature found (e.g. PGP signature). Safe enough. */
    public static final int SECURITY_BASIC_SIGNED = 1 << 2;
    /** Advanced encryption (e.g. OTR). Very strong. */
    public static final int SECURITY_ADVANCED_ENCRYPTED = 1 << 3;
    /** Advanced signature found (e.g. OTR). Very strong. */
    public static final int SECURITY_ADVANCED_SIGNED = 1 << 4;

    /*
     * Security flags for encryption status.
     * These flags, together with the ones above, help clarifying wether the
     * security features found in a message are verified and safe.
     */

    /** Digital signature verification failed. */
    public static final int SECURITY_ERROR_INVALID_SIGNATURE = 1 << 16;
    /** Invalid sender. */
    public static final int SECURITY_ERROR_INVALID_SENDER = 1 << 17;
    /** Invalid recipient. */
    public static final int SECURITY_ERROR_INVALID_RECIPIENT = 1 << 18;
    /** Invalid timestamp. */
    public static final int SECURITY_ERROR_INVALID_TIMESTAMP = 1 << 19;
    /** Invalid packet data or message parsing failed. */
    public static final int SECURITY_ERROR_INVALID_DATA = 1 << 20;
    /** Decryption failed. */
    public static final int SECURITY_ERROR_DECRYPT_FAILED = 1 << 21;
    /** Data integrity check failed. */
    public static final int SECURITY_ERROR_INTEGRITY_CHECK = 1 << 22;
    /** User's public key not available. */
    public static final int SECURITY_ERROR_PUBLIC_KEY_UNAVAILABLE = 1 << 23;

    /* Quick flags combinations. */

    /** Basic encryption (e.g. PGP). */
    public static final int SECURITY_BASIC = SECURITY_BASIC_ENCRYPTED | SECURITY_BASIC_SIGNED;


    /** Encrypts a string. */
    public byte[] encryptText(String text) throws GeneralSecurityException;

    /** Decrypts a byte array which should content text. */
    public String decryptText(byte[] encrypted, boolean verify) throws GeneralSecurityException;

    public InputStream wrapInputStream(InputStream inputStream) throws GeneralSecurityException;

    public OutputStream wrapOutputStream(OutputStream outputStream) throws GeneralSecurityException;

    public long getEncryptedLength(long decryptedLength);

}
