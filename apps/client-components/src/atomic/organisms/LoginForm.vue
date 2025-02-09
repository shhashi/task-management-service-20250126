<template>
  <form action="/login" method="POST" @submit.prevent="submit">
    <v-row>
      <v-col cols="4">
        <div class="text-h5">ログインID</div>
      </v-col>
      <v-col cols="8" >
        <v-text-field v-model="loginId" density="comfortable" />
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="4" >
        <div class="text-h5">パスワード</div>
      </v-col>
      <v-col cols="8" align-self="center">
        <v-text-field
          v-model="password"
          type="password"
          density="comfortable"
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

const loginId = ref<string>("");
const password = ref<string>("");
const isSubmitted = ref<boolean>(false);

const isInput = computed(() => {
  return loginId.value !== "" && password.value !== "";
});

async function submit() {
  isSubmitted.value = true;

  const response = await fetch("/login", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: new URLSearchParams({
      accountId: loginId.value,
      password: password.value,
    }),
  });

  if (400 <= response.status && response.status < 500) {
    alert("ログインに失敗しました。");
    isSubmitted.value = false;
  }
}
</script>
