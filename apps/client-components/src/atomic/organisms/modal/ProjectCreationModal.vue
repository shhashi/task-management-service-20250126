<template>
  <v-dialog v-model="open" max-width="400" persistent>
    <p class="text-h3">プロジェクト作成</p>

    <v-row>
      <v-col cols="4">
        <div class="text-h5">プロジェクトID</div>
      </v-col>
      <v-col cols="8">
        <v-text-field
          v-model="projectId"
          density="comfortable"
          variant="outlined"
        />
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="4">
        <div class="text-h5">プロジェクト名</div>
      </v-col>
      <v-col cols="8">
        <v-text-field
          v-model="projectName"
          density="comfortable"
          variant="outlined"
        />
      </v-col>
    </v-row>

    <v-row>
      <v-btn
        type="submit"
        color="primary"
        block
        class="mt-4"
        @click="closeModal"
      >
        キャンセル
      </v-btn>

      <v-btn
        type="submit"
        color="primary"
        block
        class="mt-4"
        @click="submit"
        :disabled="!isInput || isSubmitted"
      >
        作成
      </v-btn>
    </v-row>
  </v-dialog>
</template>

<script setup lang="ts">
import { computed, ref } from "vue";
import axios from "axios";

const url: string = import.meta.env.VITE_SERVICE_URL;

type Emits = {
  (event: "createdProject", value: string): void;
};
const emits = defineEmits<Emits>();

const open = defineModel<boolean>("open", { default: false });

const projectId = ref<string>();
const projectName = ref<string>();
const isSubmitted = ref<boolean>(false);

const isInput = computed(() => {
  return projectId.value !== "" && projectName.value !== "";
});

function closeModal() {
  open.value = false;
}

async function submit() {
  isSubmitted.value = true;

  const response = await axios.post(
    url + "/api/projects",
    {
      accountId: projectId.value,
      password: projectName.value,
    },
    {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
    },
  );

  // 認証成功時
  if (200 <= response.status && response.status < 300) {
    const projectId = response.data.projectId;
    emits("createdProject", projectId);
  }

  if (400 <= response.status && response.status < 600) {
    alert(
      "プロジェクトの作成に失敗しました。時間を開けて再度実行してください。",
    );
    isSubmitted.value = false;
  }

  closeModal();
}
</script>
