<template>
  <p class="text-h3">アカウント登録</p>
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
    </v-row>

    <v-row justify="center">
      <v-col cols="4">
        <div class="text-h5">パスワード確認</div>
      </v-col>
      <v-col cols="8" align-self="center">
        <v-text-field
          v-model="passwordConfirmation"
          type="password"
          density="comfortable"
          variant="outlined"
          :rules="rules"
        />
      </v-col>
    </v-row>

    <v-btn
      type="submit"
      color="primary"
      block
      class="mt-4"
      :disabled="!(isInput && checkSamePassword) || isSubmitted"
    >
      登録する
    </v-btn>
  </form>
</template>

<script setup lang="ts">
import { computed, ref } from "vue";
import axios from "axios";

const url: string = import.meta.env.VITE_SERVICE_URL;

type ThymeleafProps = {
  registerCode?: string;
};
let props: ThymeleafProps;
try {
  const thymeleafProps = document.getElementById("app")?.dataset?.props; // TODO Thymeleaf からの値の受け渡しがこれで本当にいいのか精査が必要
  console.log(thymeleafProps);
  if (!thymeleafProps) {
    throw new Error();
  }

  props = JSON.parse(thymeleafProps) as ThymeleafProps; // TODO 型ガードがほしい
  console.log(props);
} catch (error) {
  alert(
    "情報を取得できませんでした。お手数おかけしますが、時間をおいて再度お試しいただけますと幸いです。",
  );
  window.location.href = "/";
}

const loginId = ref<string>();
const password = ref<string>();
const passwordConfirmation = ref<string>();
const isSubmitted = ref<boolean>(false);

const isInput = computed(() => {
  return loginId.value && password.value && passwordConfirmation.value;
});

const checkSamePassword = computed(() => {
  return password.value === passwordConfirmation.value;
});

const rules = [
  (value: string) => {
    return (
      (passwordConfirmation.value && password.value === value) ||
      "パスワードが異なります。"
    );
  },
];

async function submit() {
  isSubmitted.value = true;

  const response = await axios.post(
    url + `/account/register/${props.registerCode}`,
    new URLSearchParams({
      accountId: loginId.value!,
      password: password.value!,
      passwordConfirmation: passwordConfirmation.value!
    }),
    {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
    },
  );

  if (200 <= response.status && response.status < 300) {
    window.location.href = "/";
  }

  if (400 <= response.status && response.status < 600) {
    alert("登録に失敗しました。");
    isSubmitted.value = false;
  }
}
</script>
