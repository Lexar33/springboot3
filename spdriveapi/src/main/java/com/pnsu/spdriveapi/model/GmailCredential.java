package com.pnsu.spdriveapi.model;


import jakarta.annotation.Nullable;

public record GmailCredential(
        String client_id,
        @Nullable
        String client_secret,
        @Nullable
        String refresh_token,
        @Nullable
        String grant_type,
        @Nullable
        String access_token,
        @Nullable
        String userEmail
) {

}