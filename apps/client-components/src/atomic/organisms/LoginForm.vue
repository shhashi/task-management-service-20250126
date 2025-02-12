<template>
  <p class="text-h3">ログイン</p>
  <form @submit.prevent="submit">
    <v-row>
      <v-col cols="4">
        <div class="text-h5">ログインID</div>
      </v-col>
      <v-col cols="8">
        <v-text-field
          v-model="loginId"
          density="comfortable"
          variant="outlined"
        />
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="4">
        <div class="text-h5">パスワード</div>
      </v-col>
      <v-col cols="8" align-self="center">
        <v-text-field
          v-model="password"
          type="password"
          density="comfortable"
          variant="outlined"
        />
      </v-col>
      <v-btn
        type="submit"
        color="primary"
        block
        class="mt-4"
        :disabled="!isInput || isSubmitted"
      >
        ログイン
      </v-btn>
    </v-row>
  </form>
</template>

<script setup lang="ts">
import { computed, ref } from "vue";
import axios from "axios";

const url: string = import.meta.env.VITE_SERVICE_URL;

const loginId = ref<string>("");
const password = ref<string>("");
const isSubmitted = ref<boolean>(false);

const isInput = computed(() => {
  return loginId.value !== "" && password.value !== "";
});

async function submit() {
  isSubmitted.value = true;

  const response = await axios.post(
    url + "/api/login",
    new URLSearchParams({
      accountId: loginId.value,
      password: password.value,
    }),
    {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
    },
  );

  // 認証成功時
  if (200 <= response.status && response.status < 300) {
    window.location.href = "/";
  }

  if (400 <= response.status && response.status < 600) {
    alert("ログインに失敗しました。");
    isSubmitted.value = false;
  }
}
</script>
