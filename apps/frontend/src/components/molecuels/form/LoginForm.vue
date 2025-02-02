<template>
  <form @submit.prevent="submit" action="/login" method="POST">
    <InputTitleText text="ログインID"></InputTitleText>
    <WideOneLineTextInputs v-model:input-text="loginId"></WideOneLineTextInputs>

    <InputTitleText text="パスワード"></InputTitleText>
    <WideOneLineTextInputs
      v-model:input-text="password"
      :is-password="true"
    ></WideOneLineTextInputs>
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
</template>

<script setup lang="ts">
import InputTitleText from '../../atoms/text/InputTitleText.vue';
import WideOneLineTextInputs from '../../atoms/inputs/text/WideOneLineTextInputs.vue';
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
      password: password.value,
    }),
  });

  if (400 <= response.status && response.status < 500) {
    alert('ログインに失敗しました。');
    isSubmitted.value = false;
  }
}
</script>
