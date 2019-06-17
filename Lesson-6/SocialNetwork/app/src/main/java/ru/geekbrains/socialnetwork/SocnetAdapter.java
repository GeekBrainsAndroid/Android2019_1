package ru.geekbrains.socialnetwork;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// Адаптер
public class SocnetAdapter extends RecyclerView.Adapter<SocnetAdapter.ViewHolder> {

    private String[] dataSource;
    private OnItemClickListener itemClickListener;  // Слушатель будет устанавливаться извне

    // Передаем в конструктор источник данных
    // В нашем случае это массив, но может быть и запросом к БД
    public SocnetAdapter(String[] dataSource){
        this.dataSource = dataSource;
    }

    // Создать новый элемент пользовательского интерфейса
    // Запускается менеджером
    @NonNull
    @Override
    public SocnetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Создаем новый элемент пользовательского интерфейса
        // Через Inflater
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        // Здесь можно установить всякие параметры
        return new ViewHolder(v);
    }

    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(@NonNull SocnetAdapter.ViewHolder viewHolder, int i) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран используя ViewHolder
        viewHolder.getTextView().setText(dataSource[i]);
    }

    // Вернуть размер данных, вызывается менеджером
    @Override
    public int getItemCount() {
        return dataSource.length;
    }

    // Интерфейс для обработки нажатий как в ListView
    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    // Сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    // Этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на
    // один пункт списка
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;

            // Обработчик нажатий на этом ViewHolder
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
