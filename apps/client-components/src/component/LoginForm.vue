<template>
  <v-app>
    <form
      action="/login"
      method="POST"
      @submit.prevent="submit"
    >
      <div class="text-h4">
        ログインID
      </div>
      <v-text-field v-model="loginId" />

      <div class="text-h4">
        パスワード
      </div>
      <v-text-field
        v-model="password"
        type="password"
      />
      <v-btn
        type="submit"
        color="primary"
        block
        class="mt-4"
        :disabled="!isInput || isSubmitted"
      >
        ログイン
      </v-btn>
    </form>
  </v-app>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue';

const loginId = ref<string>('');
const password = ref<string>('');
const isSubmitted = ref<boolean>(false);

const isInput = computed(() => {
  return loginId.value !== '' && password.value !== '';
});

async function submit() {
  isSubmitted.value = true;

  const response = await fetch('/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: new URLSearchParams({
      accountId: loginId.value,
      password: password.value
    })
  });

  if (400 <= response.status && response.status < 500) {
    alert('ログインに失敗しました。');
    isSubmitted.value = false;
  }
}
</script>
