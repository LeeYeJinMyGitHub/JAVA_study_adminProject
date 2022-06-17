<template>
    <v-app>
        <h2>게시판</h2>
        <v-row justify="end">
            <v-dialog v-model="dialog" persistent="persistent" max-width="600px">
                <template v-slot:activator="{ on, attrs }">
                    <v-btn color="primary" dark="dark" v-bind="attrs" v-on="on">
                        글 등록
                    </v-btn>
                </template>
                <v-card>
                    <v-card-title class="card-title">
                        <span>글 쓰기</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="12">
                                    <v-text-field label="제목*" required="required" v-model="registerBoardTitle"></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-textarea label="내용" v-model="registerBoardContents"></v-textarea>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field label="비밀번호*" type="password" required="required"></v-text-field>
                                </v-col>
                            </v-row>
                        </v-container>
                        <small color="read">*는 필수 입력항목 입니다.</small>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="red darken-1" text="text" @click="dialog = false">
                            취소
                        </v-btn>
                        <v-btn color="blue darken-1" text="text" @click="registBoard(), dialog = false, alertSucess(), refreshAll()">
                            <!-- <v-btn color="blue darken-1" text="text" @click="dialog = false"> -->
                            저장
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
    </v-app>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {dialog: false, title: '', contents: ''}
        },
        computed: {
            setParams() {
                var vm = this;
                const params = {
                    title: vm.registerBoardTitle,
                    contents: vm.registerBoardContents
                };
                return params;
            }
        },
        methods: {
            registBoard() {
                var vm = this;
                axios
                    .post('http://localhost:8080/list/savePost', vm.setParams)
                    .then(function (response) {
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            alertSucess() {
                alert('글이 등록되었습니다.')
            },
            refreshAll() {
                this.$router.go();
            }
        }
    }
</script>

<style scoped="scoped">
    #app {
        margin-top: 20px;
        height: 100px;
        margin-bottom: 5px;
    }
    * {
        text-align: center;
    }
</style>