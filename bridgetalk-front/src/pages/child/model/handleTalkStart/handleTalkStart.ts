import { getTalkStart } from "../../query";

export async function handleTalkStart(setReply: any, setErrorModalState: any){
    try {
        const data = await getTalkStart(setReply);
    } catch (err) {
        switch (err)
    }
}