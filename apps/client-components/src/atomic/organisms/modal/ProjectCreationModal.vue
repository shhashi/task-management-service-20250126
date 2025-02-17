<template>
  <v-dialog v-model="open" max-width="600">
    <v-card title="プロジェクト作成">
      <v-card-item>
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

        <v-btn
          type="submit"
          color="white"
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
      </v-card-item>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
import { computed, ref } from "vue";
import axios from "axios";
import type { Project } from "@/lib/view/ViewType";

const url: string = import.meta.env.VITE_SERVICE_URL;

type Emits = {
  (event: "createdProject", value: Project): void;
};
const emits = defineEmits<Emits>();

const open = defineModel<boolean>("open", { default: false });

const projectId = ref<string>();
const projectName = ref<string>();
const isSubmitted = ref<boolean>(false);

const isInput = computed(() => {
  return !!projectId.value && !!projectName.value;
});

function closeModal() {
  open.value = false;
}

async function submit() {
  isSubmitted.value = true;

  const response = await axios.put(url + "/api/projects", {
    projectId: projectId.value,
    projectName: projectName.value,
  });

  if (200 <= response.status && response.status < 300) {
    const eventValue: Project = {
      projectId: response.data.projectId,
      projectName: response.data.projectName,
    };
    emits("createdProject", eventValue);
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
